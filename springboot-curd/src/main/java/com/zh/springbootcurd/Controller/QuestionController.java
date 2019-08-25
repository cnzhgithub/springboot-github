package com.zh.springbootcurd.Controller;

import com.zh.springbootcurd.dto.QuestionDto;
import com.zh.springbootcurd.model.Question;
import com.zh.springbootcurd.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question")
    public String findAllByUid(@RequestParam(name = "uid") String id,
                               Model model){

        List<QuestionDto> questionList = questionService.findAllByUid(id);
        model.addAttribute("questionList",questionList);
        System.out.println(questionList);
        return "question_list";
    }
    @GetMapping("/question/each")
    public String eachQuestion(@RequestParam(name = "id") Integer id,
                               Model model){
        QuestionDto questionDto = questionService.findEachQuestion(id);
        //System.out.println(questionDto);
        model.addAttribute("questionDto",questionDto);
        return "question";
    }
    @PostMapping("question/update")
    public String update( @RequestParam(name = "description") String description,
                          @RequestParam(name = "title") String title,
                          @RequestParam(name = "tag") String tag,
                          @RequestParam(name = "id") Integer id,
                          HttpServletRequest request,
                          Model model
    ) {
        Question question = new Question();
        question.setDescription(description);
        question.setTitle(title);
        question.setTag(tag);
        question.setId(id);
        questionService.update(question);
        return "redirect:/";
    }
}
