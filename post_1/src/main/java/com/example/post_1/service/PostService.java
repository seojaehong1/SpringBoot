package com.example.post_1.service;

import com.example.post_1.domain.Post;
import com.example.post_1.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;

    public List<Post> findAll() {
        return postMapper.findAll();
    }

    public Post findById(Long id) {
        return postMapper.findById(id);
    }

    public void create(Post post) {
        postMapper.insert(post);
    }
    public void update(Post post) {
        postMapper.update(post);
    }

    public void delete(Long id) {
        postMapper.delete(id);
    }
}
