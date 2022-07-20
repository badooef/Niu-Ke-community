package com.nowcoder.community.service;


import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author wjl
* @description 针对表【user】的数据库操作Service
* @createDate 2022-07-20 16:51:06
*/
@Service
public class UserService {
@Autowired
    private UserMapper userMapper;

public User findUserById(int id){
    return userMapper.selectById(id);
}
}
