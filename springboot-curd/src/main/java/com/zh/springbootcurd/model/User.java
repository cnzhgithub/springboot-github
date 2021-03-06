package com.zh.springbootcurd.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class User {
    private Integer id;
    private String name;
    private String account_id;
    private String token;
    private Long gmt_create;
    private Long gmt_modified;
    private String avatar_url;


}
