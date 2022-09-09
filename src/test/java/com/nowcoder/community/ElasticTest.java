package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.elasticsearch.DiscussPostRepository;
import com.nowcoder.community.entity.DiscussPost;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class ElasticTest {

@Autowired
    private DiscussPostMapper discussMapper;
@Autowired
    private DiscussPostRepository discussRepository;
@Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


@Test
    public void teada(){

    discussRepository.save(discussMapper.selectDiscussPostById(241));
    discussRepository.save(discussMapper.selectDiscussPostById(242));
    discussRepository.save(discussMapper.selectDiscussPostById(243));
}
//    @Test
//    public void testInsertList() {
////        discussRepository.saveAll(discussMapper.selectDiscussPosts(101, 0, 100,0));
////        discussRepository.saveAll(discussMapper.selectDiscussPosts(102, 0, 100,0));
////        discussRepository.saveAll(discussMapper.selectDiscussPosts(103, 0, 100));
////        discussRepository.saveAll(discussMapper.selectDiscussPosts(111, 0, 100));
////        discussRepository.saveAll(discussMapper.selectDiscussPosts(112, 0, 100));
////        discussRepository.saveAll(discussMapper.selectDiscussPosts(131, 0, 100));
////        discussRepository.saveAll(discussMapper.selectDiscussPosts(132, 0, 100));
////        discussRepository.saveAll(discussMapper.selectDiscussPosts(133, 0, 100));
////        discussRepository.saveAll(discussMapper.selectDiscussPosts(134, 0, 100));
////    }

    @Test
    public void testUpdate() {
        DiscussPost post = discussMapper.selectDiscussPostById(231);
        post.setContent("我是新人,使劲灌水.");
        discussRepository.save(post);
    }

    @Test
    public void testDelete() {
        // discussRepository.deleteById(231);
        discussRepository.deleteAll();
    }


}
