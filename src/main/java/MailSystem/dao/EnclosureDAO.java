package MailSystem.dao;

import MailSystem.model.Enclosure;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EnclosureDAO {
    //根据id获取附件
    @Select("select * from enclosure where id = #{id}")
    Enclosure getEnclosureById(Integer id);

    //根据内容获取附件
    @Select("select * from enclosure where email_id = #{email_id} and file_id = #{file_id}")
    Enclosure getEnclosureByInfo(Enclosure enclosure);

    //根据邮件提取所含附件
    @Select("select * from enclosure where email_id = #{email_id}")
    List<Enclosure> getEnclosureListByEmail(Integer email_id);

    //删除邮件中包含的附件
    @Delete("delete from enclosure where email_id = #{email_id}")
    void deleteEnclosureByEmail(Integer email_id);

    //新增附件
    @Insert("insert into enclosure(email_id,file_id) values " +
            "(#{email_id},#{file_id})")
    void addEnclosure(Enclosure enclosure);

    //删除附件
    @Delete("delete from enclosure where id = #{id}")
    void deleteEnclosure(Integer id);
}
