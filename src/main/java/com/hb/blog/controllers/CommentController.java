package com.hb.blog.controllers;

import com.hb.blog.dtos.CommentDTO;
import com.hb.blog.services.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/private/comment")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{postId}")
    public ModelAndView getComments(@PathVariable final int postId){

        List<CommentDTO> comments = commentService.getPostComments(postId);
        ModelAndView mav = new ModelAndView("comment");
        mav.addObject("comments", comments);
        return mav;
    }
    @GetMapping("/{postId}/new")
    public ModelAndView formNewComment(@PathVariable final int postId) {
        CommentDTO commentDTO = new CommentDTO(postId,"","", new Date());
        ModelAndView mav = new ModelAndView("commentForm");
        mav.addObject("commentForm", commentDTO);
        mav.addObject("postId", postId);

        return mav;
    }
    @PostMapping("/new")
    public ModelAndView addNewComment(@ModelAttribute CommentDTO commentDTO) {
        commentService.addNewComment(commentDTO);
        return new ModelAndView("redirect:/private/comment/" + commentDTO.postId());
    }
}
