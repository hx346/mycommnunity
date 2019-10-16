package com.example.mycommunity.mapper;

import com.example.mycommunity.model.Question;

public interface QuestionExtMapper {
   int incView(Question record);
   int incCommentView(Question record);
}