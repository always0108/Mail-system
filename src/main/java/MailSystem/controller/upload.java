package MailSystem.controller;

import MailSystem.model.Files;
import MailSystem.service.FilesService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
public class upload {
    @Autowired
    private FilesService filesService;

//    @RequestMapping(value = "/upload",method = RequestMethod.POST)
//    @ResponseBody
//    public Files upload(@RequestParam("file") MultipartFile file) {
//        String filePath = "/home/limeng/upload/";
//        filePath = filePath + new Date().getTime() + file.getOriginalFilename();
//        File targetFile = new File(filePath);
//        if (targetFile.exists()) {
//            targetFile.delete();
//        }
//        try {
//            file.transferTo(targetFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Files files = new Files(file.getOriginalFilename(),filePath);
//        filesService.addFiles(files);
//        return files;
//    }

    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Object saveFile(@RequestParam("images") MultipartFile file) throws IOException {
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
        Files files = new Files(file.getOriginalFilename(),filePath);
        filesService.addFiles(files);
        return files;
    }
}
