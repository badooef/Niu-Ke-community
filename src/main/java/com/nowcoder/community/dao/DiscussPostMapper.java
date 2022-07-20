package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    /**
     *
     * @param userId 用户的id
     * @param offset 起始行行号
     * @param limit 每页多少条数据
     * @return
     */
    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);

    //@Param 注解用于给参数取别名 如果只有一个参数，并且在sql语句中<if>里使用，则必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);


}
