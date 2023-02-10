package com.hb.blog.controllers;

import com.hb.blog.dtos.PostDTO;
import com.hb.blog.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/private/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("") // localhost:9000/private/post
    public ModelAndView getPosts() {

        List<PostDTO> posts = postService.getPosts();
        ModelAndView mav = new ModelAndView("post");
        mav.addObject("posts", posts);
        return mav;
    }

    @GetMapping("/new")
    public ModelAndView formNewPost() {
        PostDTO postDTO = new PostDTO("","");
        ModelAndView mav = new ModelAndView("postForm");
        mav.addObject("postForm", postDTO);

        return mav;
    }

    @PostMapping("/new")
    public ModelAndView addUser(@ModelAttribute PostDTO postDTO) {

        postService.addNewPost(postDTO);
        return new ModelAndView("redirect:/private/post");
    }
}