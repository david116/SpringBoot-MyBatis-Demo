package com.happy.ssmdemo;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USER WHERE NAME = #{name}")
    User findByName(@Param("name") String name);

    //select * from user where name in ('Monica', '许晴')
    //@Select("SELECT * FROM USER WHERE NAME in #{name}")
    @Select({
            "<script>",
            "select * from user",
            "where name in",
            "<foreach collection='names' item='name' open='(' separator=',' close=')' >",
            "#{name}",
            "</foreach>",
            "</script>"
    })
    List<User> findByNames(@Param("names") List<String> names);

    // 根据年龄范围查询
    @Select("SELECT * FROM USER WHERE AGE >= #{age1} and AGE <= #{age2} ORDER BY AGE DESC")
    List<User> findByAgeScope(@Param("age1") Integer age1, @Param("age2") Integer age2);

    @Select("SELECT * FROM USER WHERE ID = #{id}")
    User findById(@Param("id") Long id);

    //select * from user where name in ('Monica', '许晴') and age >= xx and age <= xx
    // 根据年龄范围和名字查询
    /* 下行格式有错，不能用大于>=小于<=，要用between xxx and yyy,mybatis把他认为是一个xml格式。xml中不能包含<(小于号)
    @Select({
            "<script>", ...
            "and AGE >= #{age1} and AGE <= #{age2} ORDER BY AGE DESC",
            "</script>"
    }) */

    @Select({
            "<script>",
            "select * from user",
            "where name in",
            "<foreach collection='names' item='name' open='(' separator=',' close=')' >",
            "#{name}",
            "</foreach>",
            "and AGE between #{age1} and #{age2} ORDER BY AGE DESC",
            "</script>"
    })
    List<User> findByNamesAndAgeScope(@Param("names") List<String> names, @Param("age1") Integer age1, @Param("age2") Integer age2);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })
    @Select("SELECT * FROM USER")
    List<User> findAll();

    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

    @Update("UPDATE user SET age=#{age}, name=#{name} WHERE ID = #{id}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);

    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);
}