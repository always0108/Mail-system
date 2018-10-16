package MailSystem.ServiceImpl;

import MailSystem.dao.UserDao;
import MailSystem.model.Users;
import MailSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    //根据邮箱地址获取用户信息
    public Users selectByEmail(String email_address){
        return userDao.selectByEmail(email_address);
    }
    //新增用户
    public boolean addUser(Users user){
        //邮件地址不能重复
        if(selectByEmail(user.getEmail_address()) == null){
            userDao.addUser(user);
            return true;
        }
        return false;
    }

    //删除用户
    public boolean deleteUserById(Integer id){
        //用户不存在
        if(userDao.selectById(id) == null){
            return false;
        }
        userDao.deleteUserById(id);
        return true;
    }

    //修改用户信息
    public boolean updateUserById(Users user){
        //用户不存在
        if(selectByEmail(user.getEmail_address()) == null){
            return false;
        }
        userDao.updateUserById(user);
        return true;
    }
}
