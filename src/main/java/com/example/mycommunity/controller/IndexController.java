package com.example.mycommunity.controller;


import com.example.mycommunity.cache.HotTagCache;
import com.example.mycommunity.dto.PaginationDTO;
import com.example.mycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private HotTagCache hotTagCache;


    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest,
                        Model model,
                        //页码
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        //每页行数
                        @RequestParam(name = "size",defaultValue = "5") Integer size,

                        @RequestParam(name = "search", required = false) String search,
                        @RequestParam(name = "tag", required = false) String tag,
                        @RequestParam(name = "sort", required = false) String sort

    ) {


        PaginationDTO pagination = questionService.list(search, tag, sort, page, size);
        List<String> tags = hotTagCache.getHots();
        model.addAttribute("pagination", pagination);
        model.addAttribute("search", search);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", tags);
        model.addAttribute("sort", sort);
        return "index";


    }

}
