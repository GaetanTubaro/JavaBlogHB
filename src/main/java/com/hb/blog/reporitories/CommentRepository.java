package com.hb.blog.reporitories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb.blog.dtos.CommentDTO;
import com.hb.blog.models.Comment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Repository
public class CommentRepository {
    public List<Comment> getComments() {

        List<Comment> comments = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            File ressourceJson  = new ClassPathResource("comments.json").getFile();
            comments = mapper.readValue(
                    ressourceJson,
                    new TypeReference<List<Comment>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return comments;
    }
    public List<CommentDTO> getCommentsByPost(int id) {

        List<Comment> comments = this.getComments();
        List<CommentDTO> commentDTOS = new ArrayList<>();

        comments.forEach((comment) -> {
            if(comment.getPostId() == id) {
                commentDTOS.add(new CommentDTO(id,comment.getContent(),comment.getUserCreator(),new Date()));
            }
        });

        return commentDTOS;
    }
    public void add(Comment newComment) {
        ObjectMapper mapper = new ObjectMapper();
        List<Comment> comments = this.getComments();
        int newId = 0;

        for(Comment comment : comments) {
            if(comment.getId() >= newId) {
                newId = comment.getId() + 1;
            }
        }
        newComment.setId(newId);
        comments.add(newComment);

        try {
            File newFile = new File("src/main/resources/comments.json");
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(comments);
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
            writer.write(jsonString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
