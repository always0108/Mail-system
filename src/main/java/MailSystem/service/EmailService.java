package MailSystem.service;

import MailSystem.model.ov.EmailItem;
import MailSystem.model.pv.Email;

import java.util.Date;
import java.util.List;

public interface EmailService {
    //根据id获取邮件
    EmailItem getEmailById(Integer id);

    Email getOriginalEmailById(Integer id);

    //根据用户和文件夹获取相应邮件
    List<EmailItem> getReceivedEmailByDir(Integer receive_id, Integer dir_id);

    //根据用户获取所发送的邮件
    List<EmailItem> getSendedEmail(Integer send_id);

    //新增邮件
    void addEmail(Email email);

    //删除邮件
    void deleteEmail(Integer id);

    //移动邮件
    void moveEmail(Integer id, Integer pre_dir_id, Integer dir_id);

    //星标邮件
    void star(Integer id);

    //取消星标
    void cancelStar(Integer id);

    //标为已读
    void readEmail(Integer id);

    //更新邮件时间
    void updateTime(Integer id, Date time);

    //根据发送者和文件夹获取相应邮件
    List<EmailItem> getSenderdEmailByDir(Integer send_id, Integer dir_id);

    //更新邮件内容
    void updateEmail(Email email);
}
