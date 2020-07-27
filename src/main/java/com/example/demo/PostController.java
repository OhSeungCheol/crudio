package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping("/create")
    public String create() {
        Post post = new Post("title", "content", new Date(), "writer");
        postRepository.save(post);
        return "Success!!";
    }

    @RequestMapping("/readAll")
    public List<Post> readAll(){
        List<Post> postList = postRepository.findAll();
        return postList;
    }
}
