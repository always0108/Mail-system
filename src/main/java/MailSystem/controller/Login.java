package MailSystem.controller;

import MailSystem.model.pv.Users;
import MailSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Login {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("emailAddress") String emailAddress,
                        @RequestParam("password") String password,
                        Model model,
                        HttpServletRequest request){
        Users user = userService.selectByEmail(emailAddress);
        if(user == null){
            model.addAttribute("error","用户名不存在");
        }else {
            if (user.getPasswd().equals(password)){
                if(request.getSession(false) != null) {
                    request.getSession(false).invalidate();
                }
                request.getSession().setAttribute("user", user);
                return "MainPage";
            }else {
                model.addAttribute("error","密码错误");
            }
        }
        return "index";
    }

    //注销
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        if(request.getSession(false) != null) {
            request.getSession(false).invalidate();
        }
        return "index";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String getRegisterPage(){
        return "register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestParam("emailAddress") String emailAddress,
                           @RequestParam("name") String name,
                           @RequestParam("password") String password,
                           @RequestParam("checkpassword") String checkpassword,
                           HttpServletRequest request,
                           Model model){
        if(userService.selectByEmail(emailAddress) == null){
            if (password.equals(checkpassword)){
                Users user = new Users();
                user.setEmail_address(emailAddress);
                user.setName(name);
                user.setPasswd(password);
                userService.addUser(user);
                if(request.getSession(false) != null) {
                    request.getSession(false).invalidate();
                }
                user = userService.selectByEmail(emailAddress);
                request.getSession().setAttribute("user", user);
                return "MainPage";
            }else {
                model.addAttribute("error","两次密码不一致");
            }
        }else {
            model.addAttribute("error","该邮箱已经被占用");
        }
        return "register";
    }

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String MainPage(HttpServletRequest request,Model model){
        Users user = (Users) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        return "MainPage";
    }
}
