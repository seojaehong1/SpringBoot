package com.example.post0924.mapper;


import com.example.post0924.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("SELECT * FROM comment where post_id = #{postId} ORDER BY create_at ASC")
    List<Comment> findByPostId(@Param("postId") Long postId);

    @Select("INSERT INTO comment(post_id, writer, content) VALUES (#{postId}, #{writer}, #{content})")
    void insert(Comment comment);
}
