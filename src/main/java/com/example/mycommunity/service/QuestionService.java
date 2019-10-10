package com.example.mycommunity.service;

import com.example.mycommunity.dto.PaginationDTO;
import com.example.mycommunity.dto.QuestionDTO;
import com.example.mycommunity.mapper.QuestionMapper;
import com.example.mycommunity.mapper.UserMapper;
import com.example.mycommunity.modle.Question;
import com.example.mycommunity.modle.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
//spring组装mapper的处理位置

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {

        Integer totalCount = questionMapper.count();
        //校验page
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        //校验page是否合法
        if (page <= 1) {
            page = 1;
        }
        if (page >= totalPage) {
            page = totalPage;
        }


        Integer offset = size * (page - 1);

        List<Question> questionList = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);


        }
        paginationDTO.setQuestionDTOS(questionDTOList);

        paginationDTO.setPagination(totalCount, page, size);

        return paginationDTO;
    }

    public PaginationDTO listByUserId(Integer id, Integer page, Integer size) {

        Integer totalCount = questionMapper.countByUserId(id);
        //校验page
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        //校验page是否合法
        if (page <= 1) {
            page = 1;
        }
        if (page >= totalPage) {
            page = totalPage;
        }


        Integer offset = size * (page - 1);

        List<Question> questionList = questionMapper.listByUserId(id, offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);


        }
        paginationDTO.setQuestionDTOS(questionDTOList);

        paginationDTO.setPagination(totalCount, page, size);

        return paginationDTO;

    }
}
