package MailSystem.service;

import MailSystem.model.pv.Files;

public interface FilesService {
    //获取文件
    Files getFileById(Integer id);

    //新增文件
    boolean addFiles(Files files);

    //删除文件
    boolean deleteFiles(Integer id);
}
