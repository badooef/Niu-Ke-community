package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wjl
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-07-20 16:51:06
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper  {
    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(int id, int status);

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id, String password);

}




