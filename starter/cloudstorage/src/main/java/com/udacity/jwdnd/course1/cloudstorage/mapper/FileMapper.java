package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FileMapper {

    @Select("SELECT fileName FROM FILES WHERE userId = #{userId}")
    String[] getFileList(Integer userId);

    @Select("SELECT * FROM FILES WHERE userId = #{userId} AND fileName = #{fileName}")
    File getFileByFileName(Integer userId, String fileName);

    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userId, fileData) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Delete("DELETE FROM FILES WHERE userId = #{userId} AND filename = #{fileName}")
    int delete(Integer userId, String fileName);
}
