package com.hb.blog.controllers;

import com.hb.blog.dtos.PostDTO;
import com.hb.blog.services.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
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
        PostDTO postDTO = new PostDTO(null,"","", "", new Date());
        ModelAndView mav = new ModelAndView("postForm");
        mav.addObject("post", postDTO);

        return mav;
    }

    @PostMapping("/new")
    public String addPost(@Valid @ModelAttribute("post") PostDTO post, BindingResult result, Model model) {

        if(result.hasErrors()) {
            return "postForm";
        }
        postService.addNewPost(post);
        return "redirect:/private/post";
    }
}
