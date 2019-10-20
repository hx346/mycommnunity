package com.example.mycommunity.controller;

import com.example.mycommunity.dto.CommentDTO;
import com.example.mycommunity.dto.QuestionDTO;
import com.example.mycommunity.enums.CommentTypeEnum;
import com.example.mycommunity.service.CommentService;
import com.example.mycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model
    ) {
        //用问题id获取问题
        QuestionDTO questionDTO = questionService.getById(id);
        //
        List<CommentDTO> comments =commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        //累加阅读数
        questionService.incView(id);
        //把问题返回给页面
        model.addAttribute("question", questionDTO);
        //返回commentCreateDTOList
        model.addAttribute("comments", comments);

        return "question";
    }
}
