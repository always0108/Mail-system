package MailSystem.controller;

import MailSystem.model.Email;
import MailSystem.model.Enclosure;
import MailSystem.model.Users;
import MailSystem.service.EmailService;
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

        Users sender = (Users) request.getSession().getAttribute("user");
        System.out.println("发件人ID："+sender.getId());
        System.out.println("收件人ID："+rece.getId());
        System.out.println("主题："+subject);
        System.out.println("内容："+content);

        Email email = new Email(sender.getId(),rece.getId(),0,subject,content);
        emailService.addEmail(email);
        System.out.println("邮件ID："+email.getId());

        Integer email_id = email.getId();
        String[] nums = enclosures.split("\\+");
        System.out.println("附件ID：");
        for(String num:nums){
            System.out.println(num);
            Enclosure enclosure = new Enclosure(email_id,Integer.parseInt(num));
        }
        return "发送成功";
    }
}
