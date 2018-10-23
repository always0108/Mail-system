package MailSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/letter")
public class letter {
    @RequestMapping(value = "/write",method = RequestMethod.GET)
    public String getWritePage(){
        return "write";
    }
}
