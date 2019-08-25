package com.zh.springbootcurd.dto;

import com.zh.springbootcurd.model.User;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class QuestionDto {
    private  Integer id;
    private Integer uid;
    private String description;
    private String title;
    private String tag;
    private User user;
}
