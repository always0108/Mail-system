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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/folder")
public class FolderController {
    @Autowired
    private FolderService folderService;
    @Autowired
    private EmailService emailService;



    @RequestMapping(value = "/FolderDetail",method = RequestMethod.GET)
    public String FolderDetail(Model model, HttpServletRequest request,
                                 @ModelAttribute("dir_id") Integer dir_id){
        Users user = (Users) request.getSession().getAttribute("user");
        List<EmailItem> emailItems = emailService.getReceivedEmailByDir(user.getId(),dir_id);
        Integer unReadSum = 0;
        for (EmailItem emailItem:emailItems){
            if(emailItem.getIs_read() == false){
                unReadSum++;
            }
        }
        List<Folder> folders = folderService.getFolderListByOwner(user.getId());
        folders.add(new Folder(1,"收件箱",0));
        Iterator<Folder> it = folders.iterator();
        Folder thisFolder = new Folder();
        while(it.hasNext()){
            Folder temp = it.next();
            if(temp.getId() == dir_id){
                thisFolder.setOwner_id(temp.getOwner_id());
                thisFolder.setId(temp.getId());
                thisFolder.setName(temp.getName());
                it.remove();
            }
        }
        model.addAttribute("unReadSum",unReadSum);
        model.addAttribute("emailItems",emailItems);
        model.addAttribute("folders",folders);
        model.addAttribute("ThisFolder",thisFolder);
        return "folderDetail";
    }

    @RequestMapping(value = "/getFolders",method = RequestMethod.GET)
    public String searchContacts(Model model, HttpServletRequest request,
                                 @ModelAttribute("note") String note){
        Users user = (Users) request.getSession().getAttribute("user");
        List<Folder> folders = folderService.getFolderListByOwner(user.getId());
        model.addAttribute("note",note);
        model.addAttribute("folders",folders);
        return "folders";
    }

    @RequestMapping(value = "/manageCheckedEmail",method = RequestMethod.POST)
    public String manageEmail(HttpServletRequest request,
                              @Param("checkedList") String checkedList,
                              @RequestParam("type")String type,
                              @RequestParam("folder")Integer folderId,
                              @RequestParam("currentFolder")Integer currentFolder){
        String[] emailsID = checkedList.split("\\+");
        if(type.equals("1")){   //移动到垃圾箱
            for(String email_id:emailsID){
                Integer id = Integer.parseInt(email_id);
                Integer pre_dir_id = emailService.getOriginalEmailById(id).getDir_id();
                emailService.moveEmail(id,pre_dir_id,3);
            }
        }else if(type.equals("2")){ //星标邮件
            for(String email_id:emailsID){
                emailService.star(Integer.parseInt(email_id));
            }
        }else if(type.equals("3")){ //取消星标
            for(String email_id:emailsID){
                emailService.cancelStar(Integer.parseInt(email_id));
            }
        }else if(type.equals("4")){ //全部标为已读
            for(String email_id:emailsID){
                emailService.readEmail(Integer.parseInt(email_id));
            }
        } else if(type.equals("5")){ //移动到其他文件夹
            for(String email_id:emailsID){
                Integer id = Integer.parseInt(email_id);
                Integer pre_dir_id = emailService.getOriginalEmailById(id).getDir_id();
                emailService.moveEmail(Integer.parseInt(email_id),pre_dir_id,folderId);
            }
        }
        return "redirect:/folder/FolderDetail?dir_id="+currentFolder;
    }

    // 新建文件夹
    @RequestMapping(value = "/foldAdd",method = RequestMethod.POST)
    public String contactAdd(@RequestParam("name") String name,
                             HttpServletRequest request,
                             RedirectAttributes model){
        if(name.equals("")){
            model.addFlashAttribute("note","文件夹名不能为空");
            return "redirect:/folder/getFolders";
        }
        Users user = (Users) request.getSession().getAttribute("user");
        Folder folder = new Folder();
        folder.setName(name);
        folder.setOwner_id(user.getId());
        if(folderService.addFolder(folder)){
            model.addFlashAttribute("note","添加成功");
        }else {
            model.addFlashAttribute("note","添加失败");
        }
        return "redirect:/folder/getFolders";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public String deleteById(@RequestParam("id") Integer id,
                             HttpServletRequest request,
                             RedirectAttributes model){
        folderService.deleteFolder(id);
        model.addFlashAttribute("note","删除成功");
        return "redirect:/folder/getFolders";
    }

    @RequestMapping(value = "/modify",method = RequestMethod.GET)
    public String modifyById(@RequestParam("id") Integer id,
                             @RequestParam("name") String name,
                             HttpServletRequest request,
                             RedirectAttributes model){
        Users user = (Users) request.getSession().getAttribute("user");
        if(folderService.getFolderByNameAndOwner(name,id) == null){
            Folder folder = new Folder(user.getId(),name,id);
            folderService.updateFolder(folder);
            model.addFlashAttribute("note","修改成功");
        }else {
            model.addFlashAttribute("note","文件夹不能重复");
        }
        return "redirect:/folder/getFolders";
    }

}
