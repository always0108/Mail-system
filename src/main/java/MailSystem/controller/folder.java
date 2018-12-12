package MailSystem.controller;


import MailSystem.model.pv.Folder;
import MailSystem.model.pv.Users;
import MailSystem.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/folder")
public class folder {
    @Autowired
    private FolderService folderService;

    @RequestMapping(value = "/getFolders",method = RequestMethod.GET)
    public String searchContacts(Model model, HttpServletRequest request,
                                 @ModelAttribute("note") String note){
        Users user = (Users) request.getSession().getAttribute("user");
        List<Folder> folders = folderService.getFolderListByOwner(user.getId());
        model.addAttribute("note",note);
        model.addAttribute("folders",folders);
        return "folders";
    }

    // 新建文件夹
    @RequestMapping(value = "/foldAdd",method = RequestMethod.POST)
    public String contactAdd(@RequestParam("name") String name,
                             HttpServletRequest request,
                             RedirectAttributes model){
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
