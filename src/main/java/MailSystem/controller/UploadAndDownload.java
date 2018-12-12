package MailSystem.controller;

import MailSystem.model.pv.Files;
import MailSystem.service.FilesService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;

@Controller
@RequestMapping("/file")
public class UploadAndDownload {
    @Autowired
    private FilesService filesService;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
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

    @RequestMapping(value="/download",method=RequestMethod.GET)
    public void download(HttpServletResponse response,
            @RequestParam("id") Integer id) throws IOException{
        Files files = filesService.getFileById(id);
        if(files == null){
            return;
        }

        File file = new File(files.getAddress());
        FileInputStream  fis = new FileInputStream(file);
        String filename = URLEncoder.encode(files.getName(),"utf-8"); //解决中文文件名下载后乱码的问题
        byte[] buf = new byte[fis.available()];
        fis.read(buf);
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition","attachment; filename="+filename+"");
        //获取响应报文输出流对象
        ServletOutputStream  out =response.getOutputStream();
        //输出
        out.write(buf);
        out.flush();
        out.close();
    }
}
