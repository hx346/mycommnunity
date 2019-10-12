package com.example.mycommunity.mapper;

import com.example.mycommunity.modle.User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USER (NAME,ACCOUNT_ID,TOKEN,GMT_CREATE,GMT_MODIFIED,AVATAR_URL) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("SELECT * from user WHERE token = #{token}")
    User findByToken(String token);
    @Select("SELECT * from user WHERE  id= #{id}")
    User findById(@Param("id") Integer id);
    @Select("SELECT * from user WHERE  ACCOUNT_ID= #{accountId}")
    User findByAccountId(String accountId);
    @Update("UPDATE user SET NAME=#{name},TOKEN=#{token},GMT_MODIFIED=#{gmtModified},AVATAR_URL=#{avatarUrl}  WHERE id=#{id}")
    void update(User user);
}
