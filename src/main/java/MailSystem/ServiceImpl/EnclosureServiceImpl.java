package MailSystem.ServiceImpl;

import MailSystem.dao.EnclosureDAO;
import MailSystem.model.pv.Enclosure;
import MailSystem.service.EnclosureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnclosureServiceImpl implements EnclosureService {
    @Autowired
    private EnclosureDAO enclosureDAO;
    //根据id获取附件
    public Enclosure getEnclosureById(Integer id){
        return enclosureDAO.getEnclosureById(id);
    }

    //根据内容获取附件
    public Enclosure getEnclosureByInfo(Enclosure enclosure){
        return enclosureDAO.getEnclosureByInfo(enclosure);
    }

    //根据邮件提取所含附件
    public List<Enclosure> getEnclosureListByEmail(Integer email_id){
        return enclosureDAO.getEnclosureListByEmail(email_id);
    }

    //删除邮件中包含的附件
    public void deleteEnclosureByEmail(Integer email_id){
        enclosureDAO.deleteEnclosureByEmail(email_id);
    }

    //新增附件
    public void addEnclosure(Enclosure enclosure){
        enclosureDAO.addEnclosure(enclosure);
    }

    //删除附件
    public void deleteEnclosure(Integer id) {
        enclosureDAO.deleteEnclosure(id);
    }
}
