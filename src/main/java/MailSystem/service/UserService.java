package MailSystem.service;

import MailSystem.model.pv.Users;
import java.util.List;

public interface UserService {
    //获取所有用户信息
    List<Users> selectAllUser();

    //根据邮箱地址获取用户信息
    Users selectByEmail(String email_address);

    //根据id获取用户信息
    Users selectById(Integer id);

    //新增用户
    boolean addUser(Users user);

    //删除用户
    boolean deleteUserById(Integer id);

    //修改用户信息
    boolean updateUserById(Users user);
}
