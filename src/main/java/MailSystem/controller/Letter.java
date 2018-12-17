package MailSystem.controller;

import MailSystem.model.ov.EmailItem;
import MailSystem.model.pv.Email;
import MailSystem.model.pv.Enclosure;
import MailSystem.model.pv.Files;
import MailSystem.model.pv.Users;
import MailSystem.service.EmailService;
import MailSystem.service.EnclosureService;
import MailSystem.service.FilesService;
import MailSystem.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/letter")
public class Letter {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EnclosureService enclosureService;
    @Autowired
    private FilesService filesService;

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String getWritePage() {
        return "write";
    }

    @RequestMapping(value = "/reply", method = RequestMethod.GET)
    public String getReplyPage(@RequestParam("receiver") String receiver,
                               Model model) {
        model.addAttribute("receiver", receiver);
        return "reply";
    }

    @RequestMapping(value = "/deleteEnclosure", method = RequestMethod.GET)
    @ResponseBody
    public String deleteEnclosure(@RequestParam("id") Integer id) {
        Files files = filesService.getFileById(id);
        File tempFile = new File(files.getAddress());
        if (tempFile.exists()) {
            tempFile.delete();
        }
        filesService.deleteFiles(id);
        enclosureService.deleteEnclosureByFileId(id);
        return "success";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String send(@RequestParam("receiver") String receiver,
                       @RequestParam("subject") String subject,
                       @RequestParam("content") String content,
                       @RequestParam("enclosure") String enclosures,
                       @RequestParam("type") String type,
                       HttpServletRequest request,
                       Model model) {
        Integer flag = 0; // 结果标记
        Integer dir_type = 1; //收件箱默认种别码为0
        if (type.equals("1")) { //草稿箱默认种别码为1
            dir_type = 2;
        }
        Users rece = userService.selectByEmail(receiver);
        if (rece == null) {
            flag = 0;
            model.addAttribute("flag", flag);
            return "reback";
        }
        //存入邮件
        Users sender = (Users) request.getSession().getAttribute("user");
        Email email = new Email(sender.getId(), rece.getId(), dir_type, dir_type, subject, content, new Date());
        emailService.addEmail(email);
        Integer email_id = email.getId();
        if (enclosures.equals("null")) {
            //附件为空
        } else {
            //附件不为空
            String[] nums = enclosures.split("\\+");
            for (String num : nums) {
                Enclosure enclosure = new Enclosure(email_id, Integer.parseInt(num));
                enclosureService.addEnclosure(enclosure);
            }
        }
        if (type.equals("0")) {   //种别码为0时直接发送邮件
            flag = 1;
            model.addAttribute("flag", flag);
        } else {  // 草稿存储完毕
            flag = 2;
            model.addAttribute("flag", flag);
        }
        return "reback";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(@RequestParam("id") Integer id,
                         @RequestParam("receiver") String receiver,
                         @RequestParam("subject") String subject,
                         @RequestParam("content") String content,
                         @RequestParam("enclosure") String enclosures,
                         @RequestParam("type") String type,
                         HttpServletRequest request,
                         Model model) {
        Integer flag = 0; // 结果标记
        Integer dir_type = 1; //收件箱默认种别码为0
        if (type.equals("1")) { //草稿箱默认种别码为1
            dir_type = 2;
        }
        Users rece = userService.selectByEmail(receiver);
        if (rece == null) {
            flag = 0;
            model.addAttribute("flag", flag);
            return "reback";
        }

        //存入邮件
        Email email = emailService.getOriginalEmailById(id);
        email.setReceive_id(rece.getId());
        email.setSubject(subject);
        email.setContent(content);
        email.setDir_id(dir_type);
        email.setTime(new Date());
        emailService.updateEmail(email);

        Integer email_id = email.getId();
        if (!enclosures.equals("null")) {
            //附件不为空
            String[] nums = enclosures.split("\\+");
            for (String num : nums) {
                Enclosure enclosure = new Enclosure(email_id, Integer.parseInt(num));
                enclosureService.addEnclosure(enclosure);
            }
        }
        if (type.equals("0")) {   //种别码为0时直接发送邮件
            flag = 1;
            model.addAttribute("flag", flag);
        } else if (type.equals("1")) {  // 草稿存储完毕
            flag = 2;
            model.addAttribute("flag", flag);
        }
        return "reback";
    }

    @RequestMapping(value = "/getEmailById", method = RequestMethod.GET)
    public String getEmailById(HttpServletRequest request,
                               @Param("id") Integer id,
                               Model model) {
        EmailItem emailItem = emailService.getEmailById(id);
        Integer type = 0;
        String referer = request.getHeader("Referer");
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        if (referer.equals("inbox")) {
            type = 1;          // 收件箱
        } else if (referer.equals("star")) {
            type = 2;          // 星标邮件
        } else if (referer.equals("garbage")) {
            type = 3;          // 垃圾箱
        }
        if (emailItem.getIs_read().equals(false)) {
            emailService.readEmail(id);
        }
        model.addAttribute("type", type);
        model.addAttribute("emailItem", emailItem);
        return "EmailDetail";
    }

    @RequestMapping(value = "/sent", method = RequestMethod.GET)
    public String getSentEmail(HttpServletRequest request, Model model) {
        Users user = (Users) request.getSession().getAttribute("user");
        List<EmailItem> emailItems = emailService.getSendedEmail(user.getId());
        model.addAttribute("emailItems", emailItems);
        return "sent";
    }
}