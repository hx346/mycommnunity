package com.example.mycommunity.controller;


import com.example.mycommunity.dto.CommentDTO;
import com.example.mycommunity.dto.ResultDTO;
import com.example.mycommunity.exception.CustomizeErrorCode;
import com.example.mycommunity.model.Comment;
import com.example.mycommunity.model.User;
import com.example.mycommunity.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    //将对象自动序列化为json
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    //requestbody自动将json赋值到commentdto
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }


        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);

        commentService.insert(comment);
        return ResultDTO.okOf();

    }
}
