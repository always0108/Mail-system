package MailSystem.ServiceImpl;

import MailSystem.dao.FolderDAO;
import MailSystem.model.pv.Folder;
import MailSystem.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderServiceImpl implements FolderService {
    @Autowired
    private FolderDAO folderDAO;

    //根据id获取文件
    public Folder getFolderById(Integer id){
        return folderDAO.getFolderById(id);
    }

    //根据名称和用户获取文件
    public Folder getFolderByNameAndOwner(String name, Integer owner_id){
        return folderDAO.getFolderByNameAndOwner(name,owner_id);
    }

    //根据用户文件夹列表
    public List<Folder> getFolderListByOwner(Integer owner_id){
        return folderDAO.getFolderListByOwner(owner_id);
    }

    //新增文件夹
    public boolean addFolder(Folder folder){
        if(folderDAO.getFolderByNameAndOwner(folder.getName(),folder.getOwner_id())==null){
            folderDAO.addFolder(folder);
            return true;
        }else {
            return false;
        }
    }

    //删除文件夹
    public void deleteFolder(Integer id){
        folderDAO.deleteFolder(id);
    }

    //修改文件夹名称
    public void updateFolder(Folder folder){
        folderDAO.updateEmail(folder);
    }
}
