package com.hb.blog.reporitories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb.blog.models.Post;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {

    public List<Post> getPosts() {

        List<Post> posts = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            File ressourceJson  = new ClassPathResource("posts.json").getFile();
            posts = mapper.readValue(
                    ressourceJson,
                    new TypeReference<List<Post>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public void save(Post newPost) {

        ObjectMapper mapper = new ObjectMapper();
        List<Post> posts = this.getPosts();
        int newId = 0;

        for(Post post : posts) {
            if(post.getId() >= newId) {
                newId = post.getId() +1;
            }
        }
        newPost.setId(newId);
        posts.add(newPost);

        try {
            File newFile = new File("src/main/resources/posts.json");
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(posts);
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
            writer.write(jsonString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
