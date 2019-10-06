package com.example.mycommunity.mapper;

import com.example.mycommunity.modle.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USER (Name,ACCOUNT_ID,token,gmt_creart,gmt_modifide) values (#{Name},#{accoutid},#{token},#{gmtcreart},#{gmtmodifide}) ")
    void insert(User user);

    @Select("SELECT * from user WHERE token = #{token}")
    User findByToken(String token);
}
