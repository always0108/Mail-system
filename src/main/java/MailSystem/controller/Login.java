package MailSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Login {
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "MainPage";
    }

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String MainPage(){
        return "MainPage";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String getRegisterPage(){
        return "register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestParam("emailAddress") String emailAddress,
                           @RequestParam("name") String name,
                           @RequestParam("password") String password,
                           @RequestParam("checkpassword") String checkpassword){
        System.out.println(emailAddress);
        System.out.println(name);
        System.out.println(password);
        System.out.println(checkpassword);
        return "register";
    }

}
