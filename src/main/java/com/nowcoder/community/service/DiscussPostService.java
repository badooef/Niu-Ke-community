package com.nowcoder.community.service;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.util.SensitiveFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class DiscussPostService {
@Autowired
    private DiscussPostMapper discussPostMapper;

@Autowired
private SensitiveFilter sensitiveFilter;
    @Value("${caffeine.posts.max-size}")
    private int maxSize;

    @Value("${caffeine.posts.expire-seconds}")
    private int expireSeconds;

    //Cache
//帖子列表的缓存
    private LoadingCache<String,List<DiscussPost>> postListCache;
//帖子总数缓存
    private LoadingCache<Integer,Integer> postRowsCache;
@PostConstruct
public void init() {
    //初始化帖子列表缓存
postListCache = Caffeine.newBuilder()
        .maximumSize(maxSize)
        .expireAfterWrite(expireSeconds, TimeUnit.SECONDS)
        .build(new CacheLoader<String, List<DiscussPost>>() {
            @Override//该方法的意思就是当缓存中没有的时候该如何从数据库中查询出方法 参数就是返回的key
            public List<DiscussPost> load(String key) throws Exception {
if(key == null || key.length() == 0){
    throw  new IllegalArgumentException("参数错误！");
}
                String[] params = key.split(":");
if (params == null || params.length !=2){
    throw  new IllegalArgumentException("参数错误");
}
int offset  = Integer.valueOf(params[0]);
int limit = Integer.valueOf(params[1]);

//二级缓存： redis ->mysql
                return discussPostMapper.selectDiscussPosts(0,offset,limit,1);
            }
        });
    //初始化帖子总数缓存
    postRowsCache = Caffeine.newBuilder()
            .maximumSize(maxSize)
            .expireAfterWrite(expireSeconds, TimeUnit.SECONDS)
            .build(new CacheLoader<Integer, Integer>() {

                @Override
                public Integer load(Integer key) throws Exception {
                    log.debug("load post rows from DB.");
                    return discussPostMapper.selectDiscussPostRows(key);
                }
            });

}


public List<DiscussPost> findDiscussPosts(int userId,int offset,int limit,int orderMode){
   if(userId == 0 && orderMode == 1){
       return postListCache.get(offset+":"+limit);
   }
   log.debug("from DB");
    return discussPostMapper.selectDiscussPosts(userId,offset,limit,orderMode);
}


public int findDiscussPostRows(int userId){

    if(userId == 0){
        return postRowsCache.get(userId);
    }
    return discussPostMapper.selectDiscussPostRows(userId);
}
public int addDiscussPost(DiscussPost post){
    if(post == null){
        throw new IllegalArgumentException("参数不能为空");
    }
    //转义html工具
    post.setTitle(HtmlUtils.htmlEscape(post.getTitle()));
    post.setContent(HtmlUtils.htmlEscape(post.getContent()));
    //过滤敏感词
    post.setTitle(sensitiveFilter.filter(post.getTitle()));
    post.setContent(sensitiveFilter.filter(post.getContent()));
    return discussPostMapper.insertDiscussPost(post);
}

public DiscussPost findDiscussPostById(int id){
    return discussPostMapper.selectDiscussPostById(id);
}
    public int updateCommentCount(int id, int commentCount){
        return discussPostMapper.updateCommentCount(id,commentCount);
    }
    public int updateType(int id, int type) {
        return discussPostMapper.updateType(id, type);
    }

    public int updateStatus(int id, int status) {
        return discussPostMapper.updateStatus(id, status);
    }

    public int updateScore(int id, double score) {
        return discussPostMapper.updateScore(id, score);
    }

}
