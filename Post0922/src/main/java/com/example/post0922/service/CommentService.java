package com.example.post0922.service;

import com.example.post0922.domain.Comment;
import com.example.post0922.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentMapper.getComments(postId); //
    }

    public void addComment(Comment comment) {
        commentMapper.insert(comment);
    }
}
