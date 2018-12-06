package MailSystem.dao;

import MailSystem.model.pv.Email;
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
            "dir_id = #{dir_id} order by time desc")
    List<Email> getReceivedEmailByDir(
            @Param("receive_id")Integer receive_id,
            @Param("dir_id")Integer dir_id);

    //根据用户获取所发送的邮件
    @Select("select * from email where send_id = #{send_id}")
    List<Email> getSendedEmail(Integer send_id);

    //新增邮件
    @Insert("insert into email(send_id,receive_id,dir_id,subject,content,is_read,star,time) values " +
            "(#{send_id},#{receive_id},#{dir_id},#{subject},#{content},FALSE,FALSE,#{time})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void addEmail(Email email);

    //星标邮件
    @Update("update email set star = TRUE where id = #{id}")
    void star(Integer id);

    //取消星标
    @Update("update email set star = FALSE where id = #{id}")
    void cancelStar(Integer id);

    //标为已读，状态码为2
    @Update("update email set is_read = TRUE where id = #{id}")
    void readEmail(Integer id);

    //删除邮件
    @Delete("delete from email where id = #{id}")
    void deleteEmail(Integer id);

    //移动邮件
    @Update("update email set dir_id = #{dir_id} where id = #{id}")
    void updateEmail(Email email);
}
