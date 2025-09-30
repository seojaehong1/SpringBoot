package com.example.post0924.controller;
import com.example.post0924.domain.Comment;
import com.example.post0924.domain.Post;
import com.example.post0924.mapper.PostMapper;
import com.example.post0924.service.CommentService;
import com.example.post0924.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RequiredArgsConstructor
@org.springframework.stereotype.Controller
public class Controller {
    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/")
    public String main(Model model) {
        return "main";
    }

    @GetMapping("/posts")
    public String list(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 5;
        int blockSize = 10;
        int totalCount = postService.count();
        int totalPages = (int)Math.ceil((double)totalCount / pageSize);
        int offset = (page - 1) * pageSize;

        model.addAttribute("posts", postService.findPage(offset, pageSize));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        int currentBlock = (page - 1) / blockSize;
        int startPage = currentBlock * pageSize + 1;
        int endPage = Math.min(startPage + blockSize -1, totalPages);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("blockSize", blockSize);

        return "list";
    }

    @GetMapping("/posts/new")
    public String createForm(){
        return "form";
    }

    @PostMapping("/posts")
    public String create(@RequestParam String title,
                         @RequestParam String content,
                         @RequestParam String writer){
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setWriter(writer);
        postService.create(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable Long id){
        postService.delete(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "form";
    }

    @PostMapping("/posts/{id}/edit")
    public String update(@PathVariable Long id, @RequestParam String title, @RequestParam String content, @RequestParam String writer){
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setContent(content);
        post.setWriter(writer);
        postService.update(post);
        return "redirect:/posts/" + id;
    }
    // 게시글 상세 보기
    @GetMapping("/posts/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        model.addAttribute("comments", commentService.getCommentsByPostId(id));
        return "detail"; // /WEB-INF/jsp/detail.jsp
    }

    @PostMapping("/posts/{id}/comments")
    public String addComment(@PathVariable Long id,
                             @RequestParam String writer,
                             @RequestParam String content){
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setWriter(writer);
        comment.setPostId(id); // <-- 이 부분을 추가
        commentService.addComment(comment);
        return "redirect:/posts/" + id;
    }


//    @PostConstruct
//    public void init(){
//        for(int i =1; i<=100; i++){
//            Post post = new Post();
//            post.setTitle("테스트 제목 "+ i);
//            post.setContent("이것은 테스트 내용입니다. 번호: " + i);
//            post.setWriter("작성자" + (i%5+1));
//            postService.create(post);
//        }
//        System.out.println("테스트 게시글 100개 생성 완료!");
//    }

}
