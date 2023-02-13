package com.hb.blog.services;

import com.hb.blog.dtos.LocalUserDTO;
import com.hb.blog.dtos.UserFormDTO;
import com.hb.blog.models.LocalUser;
import com.hb.blog.reporitories.UserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private ValidatorFactory validatorFactory;
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, ValidatorFactory validatorFactory) {
        this.userRepository = userRepository;
        this.validatorFactory = validatorFactory;
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
        newUser.setRole("USER");

        Set<ConstraintViolation<LocalUser>> violations = validatorFactory.getValidator().validate(newUser);
        if(violations.isEmpty()) {
            newUser.setPassword(passwordEncoder.encode(userFormDTO.password()));
            userRepository.save(newUser);
        } else {
            // ....
            logger.error("Validation failed !");
            violations.forEach((violation) -> { logger.error(violation.getMessage()); });
        }

        return newUser;
    }
}
