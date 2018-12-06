package MailSystem.service;

import MailSystem.model.pv.Folder;

public interface FolderService {
    //根据id获取文件
    Folder getFolderById(Integer id);

    //根据名称和用户获取文件
    Folder getFolderByNameAndOwner(String name, Integer owner_id);

    //根据用户文件夹列表
    Folder getFolderListByOwner(Integer owner_id);

    //新增文件夹
    void addFolder(Folder folder);

    //删除文件夹
    void deleteFolder(Integer id);

    //修改文件夹名称
    void updateEmail(Folder folder);
}
