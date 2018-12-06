package MailSystem.service;

import MailSystem.model.pv.Enclosure;

import java.util.List;

public interface EnclosureService {
    //根据id获取附件
    Enclosure getEnclosureById(Integer id);

    //根据内容获取附件
    Enclosure getEnclosureByInfo(Enclosure enclosure);

    //根据邮件提取所含附件
    List<Enclosure> getEnclosureListByEmail(Integer email_id);

    //删除邮件中包含的附件
    void deleteEnclosureByEmail(Integer email_id);

    //新增附件
    void addEnclosure(Enclosure enclosure);

    //删除附件
    void deleteEnclosure(Integer id);
}
