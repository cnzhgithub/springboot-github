package com.zh.springbootcurd.Controller;

import com.zh.springbootcurd.mapper.UserMapper;
import com.zh.springbootcurd.model.PageInfo;
import com.zh.springbootcurd.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



import javax.servlet.http.HttpServletRequest;


/*
 * 测试demo1
 * */
@Controller
public class HelloController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page",defaultValue = "1") String page,
                        @RequestParam(name = "pageSize",defaultValue = "3") String pageSize ) {
        Integer pagei = Integer.valueOf(page);
        Integer pageSizei = Integer.valueOf(pageSize);
        System.out.println("1");
        PageInfo pageInfo = questionService.findAll(pagei,pageSizei);
        model.addAttribute("pageInfo",pageInfo);
        System.out.println(model);
        return "index";
    }

}
