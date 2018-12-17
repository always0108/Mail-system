package MailSystem.controller;

import MailSystem.model.ov.EmailItem;
import MailSystem.model.pv.Users;
import MailSystem.service.EmailService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/letter")
public class Draft {
    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/draft",method = RequestMethod.GET)
    public String getDraft(HttpServletRequest request, Model model){
        Users user = (Users) request.getSession().getAttribute("user");
        List<EmailItem> emailItems = emailService.getSenderdEmailByDir(user.getId(),2);
        model.addAttribute("emailItems",emailItems);
        return "draft";
    }

    @RequestMapping(value = "/modify",method = RequestMethod.GET)
    public String getModify(HttpServletRequest request,
                            @Param("id") Integer id,
                            Model model){
        EmailItem emailItem = emailService.getEmailById(id);
        model.addAttribute("emailItem",emailItem);
        return "letterModify";
    }

    @RequestMapping(value = "/manageDraft",method = RequestMethod.POST)
    public String manageDraft(HttpServletRequest request,
                              @Param("checkedList") String checkedList,
                              @RequestParam("type")String type,
                              Model model){
        String[] emailsID = checkedList.split("\\+");
        if(type.equals("1")){       //删除
            for(String email_id:emailsID){
                Integer id = Integer.parseInt(email_id);
                Integer pre_dir_id = emailService.getOriginalEmailById(id).getDir_id();
                emailService.moveEmail(id,pre_dir_id,3);
            }
        }else if(type.equals("2")){ //发送邮件
            for(String email_id:emailsID){
                emailService.moveEmail(Integer.parseInt(email_id),1,1);
                emailService.updateTime(Integer.parseInt(email_id),new Date());
            }
        }
        return "redirect:/letter/draft";
    }
}
