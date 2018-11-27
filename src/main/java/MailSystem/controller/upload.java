package MailSystem.controller;

import MailSystem.model.Files;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;

@Controller
public class upload {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String getTestPage(){
        return "test";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        String filePath = "/home/limeng/upload/";
        filePath = filePath + new Date().getTime() + file.getOriginalFilename();
        File targetFile = new File(filePath);
        if (targetFile.exists()) {
            targetFile.delete();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Files myfile = new Files(file.getOriginalFilename(),filePath);

        return "ok";
    }
}
