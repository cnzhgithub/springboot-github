package com.zh.springbootcurd.Controller;

import com.zh.springbootcurd.mapper.QuestionMapper;
import com.zh.springbootcurd.model.Question;
import com.zh.springbootcurd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;

@Controller
public class publishController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish() {

        return "publish";
    }

    @GetMapping("/publish/change")
    public String change(@RequestParam(name = "id") Integer id,
                         Model model){
        Question question = questionMapper.findEachQuestion(id);
        model.addAttribute("question",question);
        return "question_change";
    }

    @PostMapping("/publish")
    public String dopublish(
            @RequestParam(name = "description") String description,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "tag") String tag,
            HttpServletRequest request,
            Model model
    ) {
        Question question = new Question();
        question.setDescription(description);
        question.setTitle(title);
        question.setTag(tag);

        User user = (User) request.getSession().getAttribute("user");
            if(user==null){
                model.addAttribute("message","请登录");
                return "publish";
            }
            questionMapper.create(question);
            return "redirect:/";
        }

}
