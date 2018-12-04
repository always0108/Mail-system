package MailSystem.ServiceImpl;

import MailSystem.dao.EmailDAO;
import MailSystem.dao.EnclosureDAO;
import MailSystem.model.Email;
import MailSystem.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailDAO emailDAO;
    @Autowired
    private EnclosureDAO enclosureDAO;

    //根据id获取邮件
    public Email getEmailById(Integer id){
        return emailDAO.getEmailById(id);
    }

    //根据用户和文件夹获取相应邮件
    public List<Email> getReceivedEmailByDir(Integer receive_id,
                                      Integer dir_id){
        return emailDAO.getReceivedEmailByDir(receive_id,dir_id);
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
