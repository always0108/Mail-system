package MailSystem.dao;

import MailSystem.model.pv.Users;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    //获取所有用户信息
    @Select("select * from users")
    List<Users> selectAllUser();

    //根据邮箱地址获取用户信息
    @Select("select * from users where email_address = #{email_address}")
    Users selectByEmail(String email_address);

    //根据id获取用户信息
    @Select("select * from users where id = #{id}")
    Users selectById(Integer id);

    //新增用户
    @Insert("insert into users(email_address,name,passwd) values " +
            "(#{email_address},#{name},#{passwd})")
    void addUser(Users user);

    //删除用户
    @Delete("delete from users where id = #{id}")
    void deleteUserById(Integer id);

    //修改用户信息
    @Update("update users set email_address = #{email_address} ," +
            "name = #{name}, passwd = #{passwd} " +
            "where id = #{id}")
    void updateUserById(Users user);
}
