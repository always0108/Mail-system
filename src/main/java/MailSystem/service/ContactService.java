package MailSystem.service;

import MailSystem.model.Contacts;
import MailSystem.model.Users;

import java.util.List;

public interface ContactService {
    //根据用户id获取所有联系人信息
    List<Users> selectAllContacts(Integer id);

    //新增联系人
    boolean addContact(Contacts contacts);

    //删除联系人
    boolean deleteContact(Contacts contacts);
}
