package com.hb.blog.services;

import com.hb.blog.dtos.LocalUserDTO;
import com.hb.blog.dtos.UserFormDTO;
import com.hb.blog.models.LocalUser;
import com.hb.blog.reporitories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<LocalUserDTO> getUsers() {

        List<LocalUser> users = userRepository.getUsers();
        List<LocalUserDTO> usersDtos = new ArrayList<>();

        users.forEach((user) -> {
            usersDtos.add(new LocalUserDTO(user.getUsername(), user.getId(), user.getRole()) );
        });

        return usersDtos;
    }

    public LocalUser addUser(UserFormDTO userFormDTO) {
        LocalUser newUser = new LocalUser();

        newUser.setUsername(userFormDTO.username());
        newUser.setPassword(passwordEncoder.encode(userFormDTO.password()));
        newUser.setRole("USER");

        userRepository.save(newUser);

        return newUser;
    }
}
