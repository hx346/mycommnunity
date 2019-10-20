package com.example.mycommunity.mapper;

import com.example.mycommunity.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}