package MailSystem.controller;

import MailSystem.model.Contacts;
import MailSystem.model.Users;
import MailSystem.service.ContactService;
import MailSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class contacts {
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/contacts",method = RequestMethod.GET)
    public String contacts(Model model, HttpServletRequest request){
        Users user = (Users) request.getSession().getAttribute("user");
        List<Users> contacts = contactService.selectAllContacts(user.getId());
        model.addAttribute("contacts",contacts);
        return "contacts";
    }

    @RequestMapping(value = "/contactAdd",method = RequestMethod.GET)
    public String contactAddPage(Model model){
        return "contactAdd";
    }

    // 添加删除联系人
    @RequestMapping(value = "/contactAdd",method = RequestMethod.POST)
    public String contactAdd(@RequestParam("name") String name,
                             @RequestParam("email") String email,
                             HttpServletRequest request,
                             Model model){
        Users user = (Users) request.getSession().getAttribute("user");
        Users target = userService.selectByEmail(email);
        if(target != null && target.getName().equals(name)){
            Contacts contacts = new Contacts(user.getId(),target.getId());
            if(contactService.addContact(contacts)){
                model.addAttribute("note","添加成功");
            }else {
                model.addAttribute("note","添加失败");
            }
        }else{
            model.addAttribute("note","联系人信息不对");
        }
        return "contactAdd";
    }

    @RequestMapping(value = "/contactDelete",method = RequestMethod.GET)
    public String contactDelete(@RequestParam("email") String email,
                                HttpServletRequest request,
                                Model model){
        Users user = (Users) request.getSession().getAttribute("user");
        Users target = userService.selectByEmail(email);
        Contacts contacts = new Contacts(user.getId(),target.getId());
        if(contactService.deleteContact(contacts)){
            model.addAttribute("note","删除成功");
        }else {
            model.addAttribute("note","删除失败");
        }
        List<Users> contactsList = contactService.selectAllContacts(user.getId());
        model.addAttribute("contacts",contactsList);
        return "contacts";
    }
}
