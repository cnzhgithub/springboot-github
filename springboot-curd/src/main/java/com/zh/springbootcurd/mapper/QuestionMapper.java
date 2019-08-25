package com.zh.springbootcurd.mapper;

import com.zh.springbootcurd.model.Question;
import org.apache.ibatis.annotations.*;

import javax.lang.model.element.Name;
import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(description,title,tag) values(#{description},#{title},#{tag})")
    void create(Question question);
    @Select("select * from question limit #{pagei},#{pageSizei}")
    List<Question> findAll(@Param(value = "pagei") Integer pagei,
                           @Param(value = "pageSizei") Integer pageSizei);
    @Select("select count(*) from question")
    Integer findall();
    @Select("SELECT * FROM question WHERE uid=(SELECT id FROM USER WHERE account_id=#{id} LIMIT 0,1)")
    List<Question> findAllByUid(String id);
    @Select("select * from question where id=#{id}")
    Question findEachQuestion(Integer id);

    @Update("update question set description=#{description},title=#{title},tag=#{tag}")
    void update(Question question);
}
