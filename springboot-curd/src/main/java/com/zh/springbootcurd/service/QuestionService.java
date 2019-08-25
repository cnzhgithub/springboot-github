package com.zh.springbootcurd.service;

import com.zh.springbootcurd.dto.QuestionDto;
import com.zh.springbootcurd.mapper.QuestionMapper;
import com.zh.springbootcurd.mapper.UserMapper;
import com.zh.springbootcurd.model.PageInfo;
import com.zh.springbootcurd.model.Question;
import com.zh.springbootcurd.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    public PageInfo findAll(Integer pagei, Integer pageSizei) {
        PageInfo info = new PageInfo();
        Integer page = pageSizei*(pagei-1);
        Integer n = questionMapper.findall();
        //System.out.println(n);
        info.setTotalCount(n);
        //System.out.println((int)Math.ceil(n/pageSizei));
        //info.setTotalPage((int)Math.ceil(n/pageSizei));
        info.setPageSize(pageSizei);
        info.setPage(pagei);
        List<Question> list = questionMapper.findAll(page,pageSizei);
        List<QuestionDto> list1 = new ArrayList<>();
        for(Question question:list){
            User user = userMapper.findByTd(question.getUid());
            QuestionDto questionDto = new QuestionDto();
            questionDto.setUser(user);
            BeanUtils.copyProperties(question,questionDto);
            list1.add(questionDto);
        }
        info.setList(list1);
        //System.out.println(info);
        return info;
    }

    public List<QuestionDto> findAllByUid(String id) {
        List<Question> list = questionMapper.findAllByUid(id);
        List<QuestionDto> list1 = new ArrayList<>();
        for(Question question:list){
            User user = userMapper.findByUid(id);
            QuestionDto questionDto = new QuestionDto();
            questionDto.setUser(user);
            BeanUtils.copyProperties(question,questionDto);
            list1.add(questionDto);
        }
        return list1;
    }

    public QuestionDto findEachQuestion(Integer id) {
        Question question = questionMapper.findEachQuestion(id);
        User user = userMapper.findByTd(id);
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question,questionDto);
        questionDto.setUser(user);
        return questionDto;
    }

    public void update(Question question) {
        questionMapper.update(question);
    }
}
