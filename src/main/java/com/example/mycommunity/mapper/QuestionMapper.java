package com.example.mycommunity.mapper;

import com.example.mycommunity.modle.Question;
import com.example.mycommunity.modle.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface QuestionMapper {
    @Insert("INSERT INTO question (TITLE,DESCRIPTION,GMT_CREATE,GMT_MODIFIED,CREATOR,TAG) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag}) ")
    void create(Question question);

}
