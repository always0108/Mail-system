package MailSystem.ServiceImpl;

import MailSystem.dao.ContactsDao;
import MailSystem.model.Contacts;
import MailSystem.model.Users;
import MailSystem.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactsDao contactsDao;

    //根据用户id获取所有联系人信息
    public List<Users> selectAllContacts(Integer id){
        return contactsDao.selectAllContacts(id);
    }

    //新增联系人
    public boolean addContact(Contacts contacts){
        if(contactsDao.selectContact(contacts) == null){
            contactsDao.addContact(contacts);
            return true;
        }else {
            return false;
        }
    }

    //删除联系人
    public boolean deleteContact(Contacts contacts){
        if(contactsDao.selectContact(contacts) == null){
            return false;
        }else {
            contactsDao.deleteContact(contacts);
            return true;
        }
    }
}
