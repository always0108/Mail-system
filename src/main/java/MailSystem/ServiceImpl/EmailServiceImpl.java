package MailSystem.ServiceImpl;

import MailSystem.dao.EmailDAO;
import MailSystem.dao.EnclosureDAO;
import MailSystem.model.ov.EmailItem;
import MailSystem.model.pv.Email;
import MailSystem.model.pv.Enclosure;
import MailSystem.model.pv.Files;
import MailSystem.model.pv.Users;
import MailSystem.service.EmailService;
import MailSystem.service.EnclosureService;
import MailSystem.service.FilesService;
import MailSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailDAO emailDAO;
    @Autowired
    private EnclosureService enclosureService;
    @Autowired
    private UserService userService;
    @Autowired
    private FilesService filesService;

    //根据id获取邮件
    public EmailItem getEmailById(Integer id){
        Email email = emailDAO.getEmailById(id);
        List<Enclosure> enclosures = enclosureService.getEnclosureListByEmail(id);
        EmailItem emailItem = new EmailItem();

        //发送者信息
        Users sender = userService.selectById(email.getSend_id());
        emailItem.setSender(sender.getName());
        emailItem.setEmail_address(sender.getEmail_address());
        //接收者信息
        Users receiver = userService.selectById(email.getReceive_id());
        emailItem.setReceiver(receiver.getName());
        emailItem.setRece_email(receiver.getEmail_address());

        emailItem.setId(email.getId());
        emailItem.setSubject(email.getSubject());
        emailItem.setContent(email.getContent());
        emailItem.setIs_read(email.getIs_read());
        emailItem.setStar(email.getStar());
        SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        emailItem.setTime(df.format(email.getTime()));

        //添加附件
        if(enclosures == null){ //没有附件
            emailItem.setEnclosures(null);
        }else { //获取所有附件并添加
            List<Files> files = new ArrayList<>();
            for(Enclosure enclosure:enclosures){
                files.add(filesService.getFileById(enclosure.getFile_id()));
            }
            emailItem.setEnclosures(files);
        }
        return  emailItem;
    }

    public Email getOriginalEmailById(Integer id){
        return emailDAO.getEmailById(id);
    }

    //根据用户和文件夹获取相应邮件
    public List<EmailItem> getReceivedEmailByDir(Integer receive_id,
                                      Integer dir_id){
        List<Email> emails = emailDAO.getReceivedEmailByDir(receive_id,dir_id);
        List<EmailItem> emailItems = new ArrayList<>();
        for(Email email:emails){
            EmailItem emailItem = new EmailItem();
            emailItem.setId(email.getId());

            Users sender = userService.selectById(email.getSend_id());
            Users receiver = userService.selectById(receive_id);
            emailItem.setSender(sender.getName());
            emailItem.setEmail_address(sender.getEmail_address());
            emailItem.setReceiver(receiver.getName());
            emailItem.setRece_email(receiver.getEmail_address());

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
    public List<EmailItem> getSendedEmail(Integer send_id){
        List<Email> emails = emailDAO.getSendedEmail(send_id);
        List<EmailItem> emailItems = new ArrayList<>();
        for(Email email:emails){
            if(email.getDir_id() == 2)
                continue;
            EmailItem emailItem = new EmailItem();
            emailItem.setId(email.getId());
            Users receiver = userService.selectById(email.getReceive_id());
            emailItem.setReceiver(receiver.getName());
            emailItem.setRece_email(receiver.getEmail_address());
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

    //新增邮件
    public void addEmail(Email email){
        emailDAO.addEmail(email);
    }

    //删除邮件
    public void deleteEmail(Integer id){
        List<Enclosure> enclosures = enclosureService.getEnclosureListByEmail(id);
        if(enclosures != null){
            for (Enclosure enclosure:enclosures){
                String path = filesService.getFileById(enclosure.getFile_id()).getAddress();
                File file = new File(path);
                if(file.exists()){
                    file.delete();
                }
                filesService.deleteFiles(enclosure.getFile_id());
            }
            enclosureService.deleteEnclosureByEmail(id);
        }
        emailDAO.deleteEmail(id);
    }

    //移动邮件
    public void moveEmail(Integer id, Integer pre_dir_id, Integer dir_id){
        emailDAO.moveEmail(id,pre_dir_id,dir_id);
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


    //更新邮件时间
    public void updateTime(Integer id, Date time){
        emailDAO.updateTime(id,time);
    }

    //根据发送者和文件夹获取相应邮件
    public List<EmailItem> getSenderdEmailByDir(Integer send_id, Integer dir_id){
        List<Email> emails = emailDAO.getsenderdEmailByDir(send_id,dir_id);
        List<EmailItem> emailItems = new ArrayList<>();
        for(Email email:emails){
            EmailItem emailItem = new EmailItem();
            emailItem.setId(email.getId());

            Users receiver = userService.selectById(email.getReceive_id());
            Users sender = userService.selectById(send_id);
            emailItem.setReceiver(receiver.getName());
            emailItem.setRece_email(receiver.getEmail_address());
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

    //更新邮件内容
    public void updateEmail(Email email){
        emailDAO.updateEmail(email);
    }
}
