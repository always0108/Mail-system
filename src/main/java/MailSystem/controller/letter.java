package MailSystem.controller;

import MailSystem.model.Email;
import MailSystem.model.Enclosure;
import MailSystem.model.Users;
import MailSystem.service.EmailService;
import MailSystem.service.EnclosureService;
import MailSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    @ResponseBody
    @RequestMapping(value = "/write",method = RequestMethod.POST)
    public String send(@RequestParam("receiver") String receiver,
                       @RequestParam("subject") String subject,
                       @RequestParam("content") String content,
                       @RequestParam("enclosure")String enclosures,
                       HttpServletRequest request){
        Users rece = userService.selectByEmail(receiver);
        if(rece == null){
            return "联系人不存在";
        }
        //存入邮件
        Users sender = (Users) request.getSession().getAttribute("user");
        Email email = new Email(sender.getId(),rece.getId(),0,subject,content);
        emailService.addEmail(email);
        if(enclosures.equals("null")){
            //附件为空
        }else{
            //附件不为空
            Integer email_id = email.getId();
            String[] nums = enclosures.split("\\+");
            for(String num:nums){
                Enclosure enclosure = new Enclosure(email_id,Integer.parseInt(num));
                enclosureService.addEnclosure(enclosure);
            }
        }
        return "发送成功";
    }
}
