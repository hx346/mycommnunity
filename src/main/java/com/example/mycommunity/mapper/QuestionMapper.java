package com.example.mycommunity.mapper;

import com.example.mycommunity.modle.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface QuestionMapper {
    @Insert("INSERT INTO question (TITLE,DESCRIPTION,GMT_CREATE,GMT_MODIFIED,CREATOR,TAG) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag}) ")
    void create(Question question);
    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size")Integer size);
    @Select("select count(*) from QUESTION")
    Integer count();

    @Select("select * from question where creator = #{id}  limit #{offset},#{size}")
    List<Question> listByUserId(Integer id, Integer offset, Integer size);

    @Select("select count(1) from QUESTION where creator = #{id}")
    Integer countByUserId(Integer id);

    @Select("select * from question where id = #{id}")
    Question getById(Integer id);

    @Update("UPDATE question SET TITLE=#{title},DESCRIPTION=#{description},GMT_MODIFIED=#{gmtModified},TAG=#{tag}  WHERE id=#{id}")
    void update(Question question);
}
