package com.example.mycommunity.service;

import com.example.mycommunity.dto.PaginationDTO;
import com.example.mycommunity.dto.QuestionDTO;
import com.example.mycommunity.exception.CustomizeErrorCode;
import com.example.mycommunity.exception.CustomizeException;
import com.example.mycommunity.mapper.QuestionExtMapper;
import com.example.mycommunity.mapper.QuestionMapper;
import com.example.mycommunity.mapper.UserMapper;
import com.example.mycommunity.model.Question;
import com.example.mycommunity.model.QuestionExample;
import com.example.mycommunity.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
//spring组装mapper的处理位置

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    public PaginationDTO list(Integer page, Integer size) {

        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());
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


        QuestionExample questionExample = new QuestionExample();

        questionExample.setOrderByClause("GMT_CREATE desc");
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);


        }
        paginationDTO.setData(questionDTOList);

        paginationDTO.setPagination(totalCount, page);

        return paginationDTO;
    }

    public PaginationDTO list(Long id, Integer page, Integer size) {

        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(id);
        Integer totalCount = (int) questionMapper.countByExample(example);
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


        QuestionExample example1 = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(id);

        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example1, new RowBounds(offset, size));

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);


        }
        paginationDTO.setData(questionDTOList);

        paginationDTO.setPagination(totalCount, page);

        return paginationDTO;

    }

    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            questionMapper.insert(question);
        } else {
            //更新

            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            updateQuestion.setTitle(question.getTitle());

            QuestionExample example = new QuestionExample();
            example.createCriteria()
                    .andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion, example);
            if (updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }


        }
    }

    public void incView(Long id) {

        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);

    }

    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        if (StringUtils.isBlank(queryDTO.getTag())) {
            return new ArrayList<>();
        } else {
            String[] tags = StringUtils.split(queryDTO.getTag(), ",");
            String regexTag = Arrays.stream(tags).collect(Collectors.joining("|"));
            Question question = new Question();
            question.setId(queryDTO.getId());
            question.setTag(regexTag);
            List<Question> questions = questionExtMapper.selectRelated(question);
            List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(q, questionDTO);
                return questionDTO;
            }).collect(Collectors.toList());

            return questionDTOS;
        }


    }
}
