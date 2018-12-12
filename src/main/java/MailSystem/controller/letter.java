package MailSystem.controller;

import MailSystem.model.ov.EmailItem;
import MailSystem.model.pv.Email;
import MailSystem.model.pv.Enclosure;
import MailSystem.model.pv.Users;
import MailSystem.service.EmailService;
import MailSystem.service.EnclosureService;
import MailSystem.service.UserService;
import org.apache.ibatis.annotations.Param;
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
import java.util.Iterator;
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

    @RequestMapping(value = "/reply",method = RequestMethod.GET)
    public String getReplyPage(@RequestParam("receiver") String receiver,
                               Model model){
        model.addAttribute("receiver",receiver);
        return "reply";
    }

    @RequestMapping(value = "/write",method = RequestMethod.POST)
    public String send(@RequestParam("receiver") String receiver,
                       @RequestParam("subject") String subject,
                       @RequestParam("content") String content,
                       @RequestParam("enclosure")String enclosures,
                       @RequestParam("type")String type,
                       HttpServletRequest request,
                       Model model){
        Integer flag = 0; // 结果标记
        Integer dir_type = 0; //收件箱默认种别码为0
        if(type.equals("1")) { //草稿箱默认种别码为1
            dir_type = 1;
        }
        Users rece = userService.selectByEmail(receiver);
        if(rece == null){
            flag = 0;
            model.addAttribute("flag",flag);
            return "reback";
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
            flag = 1;
            model.addAttribute("flag",flag);
        }else{  // 草稿存储完毕
            flag = 2;
            model.addAttribute("flag",flag);
        }
        return "reback";
    }

    @RequestMapping(value = "/inbox",method = RequestMethod.GET)
    public String getInbox(HttpServletRequest request, Model model){
        Users user = (Users) request.getSession().getAttribute("user");
        List<EmailItem> emailItems = emailService.getReceivedEmailByDir(user.getId(),0);
        Integer unReadSum = 0;
        for (EmailItem emailItem:emailItems){
            if(emailItem.getIs_read() == false){
                unReadSum++;
            }
        }
        model.addAttribute("unReadSum",unReadSum);
        model.addAttribute("emailItems",emailItems);
        return "inbox";
    }

    @RequestMapping(value = "/getEmailById",method = RequestMethod.GET)
    public String getEmailById(HttpServletRequest request,
                               @Param("id") Integer id,
                               Model model){
        EmailItem emailItem = emailService.getEmailById(id);
        Integer type = 0;
        String referer = request.getHeader("Referer");
        referer = referer.substring(referer.lastIndexOf("/")+1,referer.length());
        if(referer.equals("inbox")){
            type = 1;          // 收件箱
        }else if(referer.equals("star")){
            type = 2;          // 星标邮件
        }else if(referer.equals("garbage")){
            type = 3;          // 垃圾箱
        }
        if(emailItem.getIs_read() == false){
            emailService.readEmail(id);
        }
        model.addAttribute("type",type);
        model.addAttribute("emailItem",emailItem);
        return "EmailDetail";
    }

    @RequestMapping(value = "/manageCheckedEmail",method = RequestMethod.POST)
    public String manageEmail(HttpServletRequest request,
                              @Param("checkedList") String checkedList,
                              @RequestParam("type")String type,
                              Model model){

        String[] emailsID = checkedList.split("\\+");
        if(type.equals("1")){   //移动到垃圾箱
            for(String email_id:emailsID){
                emailService.moveEmail(Integer.parseInt(email_id),2);
            }
        }else if(type.equals("2")){ //星标邮件
            for(String email_id:emailsID){
                emailService.star(Integer.parseInt(email_id));
            }
        }else if(type.equals("3")){ //取消星标
            for(String email_id:emailsID){
                emailService.cancelStar(Integer.parseInt(email_id));
            }
        } else if(type.equals("3")){ //移动到其他文件夹

        }
        return "redirect:/letter/inbox";
    }


    @RequestMapping(value = "/star",method = RequestMethod.GET)
    public String getStarEmail(HttpServletRequest request, Model model){
        Users user = (Users) request.getSession().getAttribute("user");
        List<EmailItem> emailItems = emailService.getReceivedEmailByDir(user.getId(),0);
        Iterator<EmailItem> itemIterator = emailItems.iterator();
        while(itemIterator.hasNext()){
            EmailItem emailItem = itemIterator.next();
            if(emailItem.getStar() == false){
                itemIterator.remove();
            }
        }
        model.addAttribute("emailItems",emailItems);
        return "star";
    }

    @RequestMapping(value = "/cancelStarCheckedEmail",method = RequestMethod.POST)
    public String cancelStarEmail(HttpServletRequest request,
                               @Param("checkedList") String checkedList,
                               @RequestParam("type")String type,
                               Model model) {
        String[] emailsID = checkedList.split("\\+");
        for (String email_id : emailsID) {
            emailService.cancelStar(Integer.parseInt(email_id));
        }
        return "redirect:/letter/star";
    }


    @RequestMapping(value = "/garbage",method = RequestMethod.GET)
    public String getGarbage(HttpServletRequest request, Model model){
        Users user = (Users) request.getSession().getAttribute("user");
        List<EmailItem> emailItems = emailService.getReceivedEmailByDir(user.getId(),2);
        model.addAttribute("emailItems",emailItems);
        return "garbage";
    }

    @RequestMapping(value = "/manageGarbage",method = RequestMethod.POST)
    public String manageGarbage(HttpServletRequest request,
                              @Param("checkedList") String checkedList,
                              @RequestParam("type")String type,
                              Model model){
        String[] emailsID = checkedList.split("\\+");
        if(type.equals("1")){       //彻底删除
            for(String email_id:emailsID){
                emailService.deleteEmail(Integer.parseInt(email_id));
            }
        }else if(type.equals("2")){ //恢复邮件
            for(String email_id:emailsID){
                emailService.moveEmail(Integer.parseInt(email_id),0);
            }
        }
        return "redirect:/letter/garbage";
    }
}
