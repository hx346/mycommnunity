package com.example.mycommunity.controller;


import com.example.mycommunity.dto.PaginationDTO;
import com.example.mycommunity.mapper.UserMapper;
import com.example.mycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest,
                        Model model,
                        //页码
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        //每页行数
                        @RequestParam(name = "size",defaultValue = "5") Integer size

    ) {


        PaginationDTO paginationDTO = questionService.list(page,size);
        model.addAttribute("pagination", paginationDTO);

        return "index";
    }

}
