package com.example.mycommunity.service;

import com.example.mycommunity.enums.CommentTypeEnum;
import com.example.mycommunity.exception.CustomizeErrorCode;
import com.example.mycommunity.exception.CustomizeException;
import com.example.mycommunity.mapper.CommentMapper;
import com.example.mycommunity.mapper.QuestionExtMapper;
import com.example.mycommunity.mapper.QuestionMapper;
import com.example.mycommunity.model.Comment;
import com.example.mycommunity.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType()==null|| !CommentTypeEnum.isExist(comment.getType())){
            throw new  CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment doComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (doComment ==null){
                throw new  CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }else {
                commentMapper.insert(comment);
            }

        }else{
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question ==null){
                throw new  CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);

            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentView(question);

        }

    }
}
