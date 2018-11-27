package MailSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;

@Controller
@RequestMapping("/letter")
public class letter {
    @RequestMapping(value = "/write",method = RequestMethod.GET)
    public String getWritePage(){
        return "write";
    }

    @ResponseBody
    @RequestMapping(value = "/write",method = RequestMethod.POST)
    public String send(@RequestParam("receiver") String receiver,
                       @RequestParam("subject") String subject,
                       @RequestParam("content") String content){
        System.out.println(receiver);
        System.out.println(subject);
        System.out.println(content);
        return "write";
    }
}
