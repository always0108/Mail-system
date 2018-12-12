package MailSystem.dao;

import MailSystem.model.pv.Folder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FolderDAO {
    //根据id获取文件
    @Select("select * from folder where id = #{id}")
    Folder getFolderById(Integer id);

    //根据名称和用户获取文件
    @Select("select * from folder where name = #{name} and owner_id = #{owner_id}")
    Folder getFolderByNameAndOwner(
            @Param("name")String name,
            @Param("owner_id")Integer owner_id);

    //根据用户文件夹列表
    @Select("select * from folder where owner_id = #{owner_id}")
    List<Folder> getFolderListByOwner(Integer owner_id);

    //新增文件夹
    @Insert("insert into folder(name,owner_id) values " +
            "(#{name},#{owner_id})")
    void addFolder(Folder folder);

    //删除文件夹
    @Delete("delete from folder where id = #{id}")
    void deleteFolder(Integer id);

    //修改文件夹名称
    @Update("update folder set name = #{name} where id = #{id}")
    void updateEmail(Folder folder);
}
