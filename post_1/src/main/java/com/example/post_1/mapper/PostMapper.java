package com.example.post_1.mapper;

import com.example.post_1.domain.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select("SELECT * FROM post ORDER BY id DESC ")
    List<Post> findAll();

    @Select("SELECT * FROM post WHERE id = #{id}")
    Post findById(Long id);

    @Insert("INSERT INTO post(title, content, writer) VALUES(#{title},#{content},#{writer})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Post post);

    @Update("UPDATE post SET title = #{title}, content = #{content}, writer = #{writer} WHERE id = #{id}")
    void update(Post post);

    @Delete("DELETE FROM post WHERE id = #{id}")
    void delete(Long id);
}
