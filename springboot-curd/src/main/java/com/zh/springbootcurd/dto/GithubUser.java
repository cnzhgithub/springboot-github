package com.zh.springbootcurd.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class GithubUser {
    private String name;
    private Integer id;
    private String bio;
    private String avatar_url;

}
