package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void create() {
        Post post = new Post("title", "content", new Date(), "writer");
        postRepository.save(post);
    }

    @RequestMapping(value = "/readAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> readAll(){
        List<Post> postList = postRepository.findAll();
        return postList;
    }

    @RequestMapping(value = "/readOne", method = RequestMethod.GET)
    @ResponseBody
    public Post readOne(){
        return postRepository.findById(0L).orElseGet(Post::new);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(){
        //
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(){
        //
    }

}
