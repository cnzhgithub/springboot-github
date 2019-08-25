package com.zh.springbootcurd.mapper;

import com.zh.springbootcurd.model.Question;
import com.zh.springbootcurd.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified},#{avatar_url})")
     void insert(User user);
@Select("select * from user where token=#{value}")
    User findByToken(String value);
    @Select("select * from user where id=#{uid}")
    User findByTd(Integer uid);
    @Select("select * from user where account_id=#{id} limit 0,1")
    User findByUid(String id);
    @Select("select * from user where account_id=#{account_id}")
    User findByAccountid(String account_id);
    @Update("update user set name=#{name},token=#{token},gmt_create=#{gmt_create},gmt_modified=#{gmt_modified},avatar_url=#{avatar_url} where id=#{id}")
    void update(User db);
}
