package MailSystem.ServiceImpl;

import MailSystem.dao.FilesDAO;
import MailSystem.model.Files;
import org.springframework.beans.factory.annotation.Autowired;

public class FilesServiceImpl {
    @Autowired
    private FilesDAO filesDAO;
    //获取文件
    public Files getFileById(Integer id){
        return filesDAO.getFileById(id);
    }

    //新增文件
    public boolean addFiles(Files files){
        filesDAO.addFile(files);
        return true;
    }

    //删除文件
    public boolean deleteFiles(Integer id){
        if(filesDAO.getFileById(id) == null){
            return false;
        }else {
            filesDAO.deleteFile(id);
            return true;
        }
    }
}
