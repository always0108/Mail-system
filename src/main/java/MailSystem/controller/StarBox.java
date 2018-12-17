package MailSystem.controller;

import MailSystem.model.ov.EmailItem;
import MailSystem.model.pv.Folder;
import MailSystem.model.pv.Users;
import MailSystem.service.EmailService;
import MailSystem.service.FolderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/letter")
public class StarBox {

    @Autowired
    private EmailService emailService;
    @Autowired
    private FolderService folderService;

    @RequestMapping(value = "/star",method = RequestMethod.GET)
    public String getStarEmail(HttpServletRequest request, Model model){
        Users user = (Users) request.getSession().getAttribute("user");
        List<EmailItem> emailItems = emailService.getReceivedEmailByDir(user.getId(),1);
        List<Folder> folders = folderService.getFolderListByOwner(user.getId());
        for(Folder folder:folders){
            emailItems.addAll(emailService.getReceivedEmailByDir(user.getId(),folder.getId()));
        }
        Iterator<EmailItem> itemIterator = emailItems.iterator();
        while(itemIterator.hasNext()){
            EmailItem emailItem = itemIterator.next();
            if(emailItem.getStar() == false){
                itemIterator.remove();
            }
        }
        emailItems.sort(comparator);
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


    static Comparator<EmailItem> comparator = new Comparator<EmailItem>() {
        @Override
       public int compare(EmailItem p1 , EmailItem p2 ){
            return -p1.getTime().compareTo(p2.getTime());
        }
    };
}
