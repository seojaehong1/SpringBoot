package com.example.jpa0926;

import com.example.jpa0926.entity.Post;
import com.example.jpa0926.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class Jpa0926ApplicationTests {

    @Autowired
    private PostRepository postRepository;

    @Test
    void insertPost() {
        Post post = Post.builder().
                title("title3").
                content("content3").
                author("author3").
                build();
        postRepository.save(post);
    }

    @Test
    void findById() {
          Optional<Post> post = postRepository.findById(1L);
//        Post post = postRepository.findById(2L).get();
//        Post post = postRepository.findById(2l).orElse(null);
        System.out.println(post);
    }

    @Test
    void findAll() {
        List<Post> posts = postRepository.findAll();
        for(Post post : posts) {
            System.out.println(post);
        }
    }

    @Test
    void updatePost() {
        Post post = postRepository.findById(1L).get();
        post.setTitle("title1");
        post.setContent("content1");
        post.setAuthor("author1");
        postRepository.save(post);
    }

    @Test
    void deletePost() {
        postRepository.deleteById(2L);
    }

}
