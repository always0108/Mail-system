package MailSystem.service;

import MailSystem.model.Users;

public interface UserService {
    //根据邮箱地址获取用户信息
    Users selectByEmail(String email_address);

    //新增用户
    boolean addUser(Users user);

    //删除用户
    boolean deleteUserById(Integer id);

    //修改用户信息
    boolean updateUserById(Users user);
}
