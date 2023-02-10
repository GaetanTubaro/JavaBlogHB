package com.hb.blog.services;

import com.hb.blog.dtos.PostDTO;
import com.hb.blog.models.Post;
import com.hb.blog.reporitories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDTO> getPosts() {
        List<Post> posts = postRepository.getPosts();
        List<PostDTO> postsDtos = new ArrayList<>();

        posts.forEach((post) -> { postsDtos.add( new PostDTO(post.getTitle(), post.getContent())); });

        return postsDtos;
    }

    public void addNewPost(PostDTO postDTO) {
        Post newPost = new Post();

        newPost.setTitle(postDTO.title());
        newPost.setContent(postDTO.content());

        postRepository.save(newPost);
    }
}
