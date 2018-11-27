package MailSystem.dao;

import MailSystem.model.Email;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmailDAO {
    //根据id获取邮件
    @Select("select * from email where id = #{id}")
    Email getEmailById(Integer id);

    //根据用户和文件夹获取相应邮件
    @Select("select * from email where receive_id = #{receive_id} and " +
            "dir_id = #{dir_id}")
    List<Email> getReceivedEmailByDir(
            @Param("receive_id")Integer receive_id,
            @Param("dir_id")Integer dir_id);

    //根据用户获取所发送的邮件
    @Select("select * from email where send_id = #{send_id}")
    List<Email> getSendedEmail(Integer send_id);

    //新增邮件
    @Insert("insert into email(send_id,receive_id,dir_id,subject,content) values " +
            "(#{send_id},#{receive_id},#{dir_id},#{subject},#{content})")
    void addEmail(Email email);

    //删除邮件
    @Delete("delete from email where id = #{id}")
    void deleteEmail(Integer id);

    //移动邮件
    @Update("update email set dir_id = #{dir_id} where id = #{id}")
    void updateEmail(Email email);
}
