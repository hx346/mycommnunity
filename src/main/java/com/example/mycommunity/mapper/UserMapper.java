package com.example.mycommunity.mapper;

import com.example.mycommunity.modle.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user (name,accounId,token,gmtCreat,getModifide) values (#{name},#{accounId},#{token},#{gmtCreat},#{getModifide}) ")
    void insert(User user);

}
