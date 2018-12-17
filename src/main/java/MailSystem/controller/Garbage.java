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
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/letter")
public class Garbage {
    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/garbage",method = RequestMethod.GET)
    public String getGarbage(HttpServletRequest request, Model model){
        Users user = (Users) request.getSession().getAttribute("user");
        List<EmailItem> receiveList = emailService.getReceivedEmailByDir(user.getId(),3);
        List<EmailItem> sendList = emailService.getSenderdEmailByDir(user.getId(),3);
        Iterator<EmailItem> it = sendList.iterator();
        while(it.hasNext()){
            EmailItem emailItem = it.next();
            if(emailItem.getReceiver().equals(emailItem.getSender())){
                it.remove();
            }
        }

        model.addAttribute("receiveList",receiveList);
        model.addAttribute("sendList",sendList);
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
                Integer id = Integer.parseInt(email_id);
                Integer pre_dir_id = emailService.getOriginalEmailById(id).getPre_dir_id();
                emailService.moveEmail(id,pre_dir_id,pre_dir_id);
            }
        }
        return "redirect:/letter/garbage";
    }
}
