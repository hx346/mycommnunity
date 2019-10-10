package com.example.mycommunity.dto;


import com.example.mycommunity.modle.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOS;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;

    private Integer page;

    private List<Integer> pages = new ArrayList<>();

    private Integer totalPage;


    public void setPagination(Integer totalCount, Integer page, Integer size) {
        //最大页

        this.page = page;


        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        //校验page是否合法
        if (page < 1) {
            page = 1;
        }
        if (page >= totalPage) {
            page = totalPage;
        }


        for (int i = 0; i < 4; i++) {
            if (page - 3 > 0) {
                pages.add(0, page - i);
                continue;
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }

        }


        //是否展示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        //是否展示下一页
        if (page == totalPage) {
            showNext = false;
        }else
        {
            showNext = true;
        }
        //是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //是否展示最后页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }


    }
}
