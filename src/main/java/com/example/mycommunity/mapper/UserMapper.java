package com.example.mycommunity.mapper;

import com.example.mycommunity.modle.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USER (NAME,ACCOUNT_ID,TOKEN,GMT_CREATE,GMT_MODIFIED,AVATAR_URL) values (#{Name},#{accoutid},#{token},#{gmtcreat},#{gmtmodifide},#{avatarurl})")
    void insert(User user);

    @Select("SELECT * from user WHERE token = #{token}")
    User findByToken(String token);
    @Select("SELECT * from user WHERE  id= #{id}")
    User findById(@Param("id") Integer id);
}
