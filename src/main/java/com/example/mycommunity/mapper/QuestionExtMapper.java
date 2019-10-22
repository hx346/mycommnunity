package com.example.mycommunity.mapper;

import com.example.mycommunity.model.Question;

import java.util.List;

public interface QuestionExtMapper {
   int incView(Question record);
   int incCommentView(Question record);
   List<Question> selectRelated(Question question);
}