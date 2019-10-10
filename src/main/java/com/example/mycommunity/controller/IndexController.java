package com.example.mycommunity.controller;


import com.example.mycommunity.dto.PaginationDTO;
import com.example.mycommunity.dto.QuestionDTO;
import com.example.mycommunity.mapper.UserMapper;
import com.example.mycommunity.modle.User;
import com.example.mycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        //因为此判断如果name等于"token"字符串，也就是名字跟数据库中token名字相同，那么他的值就是token的value，看看数据库就想通了
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        httpServletRequest.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        PaginationDTO paginationDTO = questionService.list(page,size);
        model.addAttribute("pagination", paginationDTO);

        return "index";
    }

}
