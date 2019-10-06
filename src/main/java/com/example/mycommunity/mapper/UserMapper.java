package com.example.mycommunity.mapper;

import com.example.mycommunity.modle.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USER (Name,ACCOUNT_ID,token,gmt_creart,gmt_modifide) values (#{Name},#{accoutid},#{token},#{gmtcreart},#{gmtmodifide}) ")
    void insert(User user);

}
