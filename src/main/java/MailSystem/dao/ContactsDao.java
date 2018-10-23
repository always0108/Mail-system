package MailSystem.dao;

import MailSystem.model.Contacts;
import MailSystem.model.Users;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ContactsDao {
    //根据用户id获取所有联系人信息
    @Select("select * from users where id in (select contact_id from contacts where self_id = #{id})")
    List<Users> selectAllContacts(Integer id);

    @Select("select * from contacts where self_id = #{self_id} and contact_id = #{contact_id}")
    Contacts selectContact(Contacts contacts);

    //新增联系人
    @Insert("insert into contacts(self_id,contact_id) values " +
            "(#{self_id},#{contact_id})")
    void addContact(Contacts contacts);

    //删除联系人
    @Delete("delete from contacts where self_id = #{self_id} and contact_id = #{contact_id}")
    void deleteContact(Contacts contacts);
}
