package com.hb.blog.dtos;

import java.util.Date;

public record  PostDTO(Integer id, String title, String content, String user, Date date) {
}
