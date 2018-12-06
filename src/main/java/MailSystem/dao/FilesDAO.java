package MailSystem.dao;

import MailSystem.model.pv.Files;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FilesDAO {
    //获取文件
    @Select("select * from files where id = #{id}")
    Files getFileById(Integer id);

    //新增文件
    @Insert("insert into files(name,address) values " +
            "(#{name},#{address})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void addFile(Files files);

    //删除联系人
    @Delete("delete from files where id = #{id}")
    void deleteFile(Integer id);
}
