package com.lagou.dao;

import com.lagou.pojo.User;

import java.util.List;

public interface IUserDao {

    //查询所有用户
    List<User> findAll() throws Exception;


    //根据条件进行用户查询
    User findByCondition(User user) throws Exception;

    //新增用户信息
    void insert(User user) throws Exception;

    //删除用户信息
    void update(User user) throws Exception;
    //删除用户信息
    void delete(User user)throws  Exception;


}
