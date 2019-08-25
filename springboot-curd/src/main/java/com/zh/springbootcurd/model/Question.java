package com.zh.springbootcurd.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Question {
    private Integer uid;
    private Integer id;
    private String description;
    private String title;
    private String tag;

}
