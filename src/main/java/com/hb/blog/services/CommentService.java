package com.hb.blog.services;

import com.hb.blog.dtos.CommentDTO;
import com.hb.blog.models.Comment;
import com.hb.blog.reporitories.CommentRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentDTO> getPostComments(int id) {
        List<Comment> comments = commentRepository.getComments();
        List<CommentDTO> commentDTOS = new ArrayList<>();

        comments.forEach((comment) -> {
            if(comment.getPostId() == id) {
                commentDTOS.add(new CommentDTO(id,comment.getContent(),comment.getUserCreator(),new Date()));
            }
        });

        return commentDTOS;
    }

    public void addNewComment(CommentDTO commentDTO) {
        Comment newComment = new Comment();
        newComment.setPostId(commentDTO.postId());
        newComment.setContent(commentDTO.content());
        newComment.setUserCreator(SecurityContextHolder.getContext().getAuthentication().getName());
        newComment.setDate(new Date());

        commentRepository.add(newComment);
    }
}
