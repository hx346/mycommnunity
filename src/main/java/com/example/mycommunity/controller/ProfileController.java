package com.example.mycommunity.controller;

import com.example.mycommunity.dto.PaginationDTO;
import com.example.mycommunity.mapper.UserMapper;
import com.example.mycommunity.model.User;
import com.example.mycommunity.service.NotificationService;
import com.example.mycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;


    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest httpServletRequest,
                          //页码
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          //每页行数
                          @RequestParam(name = "size",defaultValue = "5") Integer size
    ) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }


        if ("questions".equals(action)) {
            PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            model.addAttribute("pagination",paginationDTO);
        } else if ("replies".equals(action)) {
            PaginationDTO paginationDTO =notificationService.list(user.getId(),page,size);
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
            model.addAttribute("pagination", paginationDTO);

        }
        return "profile";
    }
}
