package MailSystem.ServiceImpl;

import MailSystem.dao.EmailDAO;
import MailSystem.dao.EnclosureDAO;
import MailSystem.model.ov.EmailItem;
import MailSystem.model.pv.Email;
import MailSystem.model.pv.Users;
import MailSystem.service.EmailService;
import MailSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailDAO emailDAO;
    @Autowired
    private EnclosureDAO enclosureDAO;
    @Autowired
    private UserService userService;

    //根据id获取邮件
    public Email getEmailById(Integer id){
        return emailDAO.getEmailById(id);
    }

    //根据用户和文件夹获取相应邮件
    public List<EmailItem> getReceivedEmailByDir(Integer receive_id,
                                      Integer dir_id){
        List<Email> emails = emailDAO.getReceivedEmailByDir(receive_id,dir_id);
        List<EmailItem> emailItems = new ArrayList<>();
        for(Email email:emails){
            Users sender = userService.selectById(email.getSend_id());
            EmailItem emailItem = new EmailItem();
            emailItem.setId(email.getId());
            emailItem.setSender(sender.getName());
            emailItem.setEmail_address(sender.getEmail_address());
            emailItem.setSubject(email.getSubject());
            emailItem.setContent(email.getContent());
            emailItem.setIs_read(email.getIs_read());
            emailItem.setStar(email.getStar());
            SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
            emailItem.setTime(df.format(email.getTime()));
            emailItems.add(emailItem);
        }
        return emailItems;
    }

    //根据用户获取所发送的邮件
    public List<Email> getSendedEmail(Integer send_id){
        return emailDAO.getSendedEmail(send_id);
    }

    //新增邮件
    public void addEmail(Email email){
        emailDAO.addEmail(email);
    }

    //删除邮件
    public void deleteEmail(Integer id){
        enclosureDAO.deleteEnclosureByEmail(id);
        emailDAO.deleteEmail(id);
    }

    //移动邮件
    public void updateEmail(Email email){
        emailDAO.updateEmail(email);
    }

    //星标邮件
    public void star(Integer id){
        emailDAO.star(id);
    }

    //取消星标
    public void cancelStar(Integer id){
        emailDAO.cancelStar(id);
    }

    //标为已读
    public void readEmail(Integer id){
        emailDAO.readEmail(id);
    }
}
