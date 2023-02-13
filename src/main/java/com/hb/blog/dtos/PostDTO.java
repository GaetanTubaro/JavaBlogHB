package com.hb.blog.dtos;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
public record  PostDTO(Integer id,
                       @NotBlank(message = "Title is mandatory") String title,
                       @NotBlank(message = "Content is mandatory") String content,
                       String user,
                       Date date) {}
