package com.hb.blog.dtos;

import java.util.Date;

public record CommentDTO(int postId, String content, String userCreator, Date date) {
}
