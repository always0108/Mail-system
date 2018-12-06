package MailSystem.controller;

import MailSystem.model.ov.EmailItem;
import MailSystem.model.pv.Email;
import MailSystem.model.pv.Enclosure;
import MailSystem.model.pv.Users;
import MailSystem.service.EmailService;
import MailSystem.service.EnclosureService;
import MailSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/letter")
public class letter {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EnclosureService enclosureService;

    @RequestMapping(value = "/write",method = RequestMethod.GET)
    public String getWritePage(){
        return "write";
    }

    @RequestMapping(value = "/write",method = RequestMethod.POST)
    @ResponseBody
    public String send(@RequestParam("receiver") String receiver,
                       @RequestParam("subject") String subject,
                       @RequestParam("content") String content,
                       @RequestParam("enclosure")String enclosures,
                       @RequestParam("type")String type,
                       HttpServletRequest request){
        Integer dir_type = 0; //收件箱默认种别码为0
        if(type.equals("1")) { //草稿箱默认种别码为1
            dir_type = 1;
        }
        Users rece = userService.selectByEmail(receiver);
        if(rece == null){
            return "联系人不存在";
        }
        //存入邮件
        Users sender = (Users) request.getSession().getAttribute("user");
        Email email = new Email(sender.getId(),rece.getId(),dir_type,subject,content,new Date());
        emailService.addEmail(email);
        Integer email_id = email.getId();
        if(enclosures.equals("null")){
            //附件为空
        }else{
            //附件不为空
            String[] nums = enclosures.split("\\+");
            for(String num:nums){
                Enclosure enclosure = new Enclosure(email_id,Integer.parseInt(num));
                enclosureService.addEnclosure(enclosure);
            }
        }
        if(type.equals("0")){   //种别码为0时直接发送邮件
            return "发送成功";
        }else{  // 草稿存储完毕
            return "草稿存储完毕";
        }
    }

    @RequestMapping(value = "/inbox",method = RequestMethod.GET)
    public String getInbox(HttpServletRequest request, Model model){
        Users user = (Users) request.getSession().getAttribute("user");
        List<EmailItem> emailItems = emailService.getReceivedEmailByDir(user.getId(),0);
        model.addAttribute("emailItems",emailItems);
        return "inbox";
    }
}
