package com.zh.springbootcurd.model;

import com.zh.springbootcurd.dto.QuestionDto;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Data
public class PageInfo {
    private Integer page;//当前页
    private Integer pageSize;//当前页的数据量
    private Integer totalPage;//总页数
    private Integer totalCount;//数据总数
    private List<QuestionDto> list;//数据项集合
    private boolean endPge;

    public Integer getTotalPage() {
        if (totalCount%pageSize==0) {
            return (int) Math.ceil(totalCount / pageSize);
        }
        return (int) Math.ceil(totalCount / pageSize)+1;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isEndPge() {
        if (page==totalPage){
            endPge = false;
        }else {
            endPge = true;
        }
        return endPge;
    }

    public void setEndPge(boolean endPge) {
        this.endPge = endPge;
    }
}
