package MailSystem.service;

import MailSystem.model.Email;
import java.util.List;

public interface EmailService {
    //根据id获取邮件
    Email getEmailById(Integer id);

    //根据用户和文件夹获取相应邮件
    List<Email> getReceivedEmailByDir(Integer receive_id, Integer dir_id);

    //根据用户获取所发送的邮件
    List<Email> getSendedEmail(Integer send_id);

    //新增邮件
    void addEmail(Email email);

    //删除邮件
    void deleteEmail(Integer id);

    //移动邮件
    void updateEmail(Email email);

    //星标邮件
    void star(Integer id);

    //取消星标
    void cancelStar(Integer id);

    //标为已读
    void readEmail(Integer id);
}
