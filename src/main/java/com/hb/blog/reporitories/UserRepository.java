package com.hb.blog.reporitories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb.blog.models.LocalUser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    public List<LocalUser> getUsers() {

        List<LocalUser> users = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            File ressourceJson  = new ClassPathResource("users.json").getFile();
            users = mapper.readValue(
                    ressourceJson,
                    new TypeReference<List<LocalUser>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
    public LocalUser getUserByUsername(String username) {

        List<LocalUser> users = this.getUsers();

        for(LocalUser user : users) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void save(LocalUser user) {

        ObjectMapper mapper = new ObjectMapper();
        List<LocalUser> existingUsers = this.getUsers();
        int newId = 0;

        for(LocalUser existingUser : existingUsers) {
            if(existingUser.getId() >= newId) {
                newId = existingUser.getId() +1;
            }
        }
        user.setId(newId);
        existingUsers.add(user);

        try {
            File newFile = new File("src/main/resources/users.json");
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(existingUsers);
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
            writer.write(jsonString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
