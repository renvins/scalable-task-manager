package org.github.scalabletaskmanager.user.service.impl;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.github.scalabletaskmanager.gen.model.RegisterUserDTO;
import org.github.scalabletaskmanager.user.exception.UserAlreadyExistsException;
import org.github.scalabletaskmanager.user.service.UserService;
import org.github.scalabletaskmanager.user.sql.UserEntity;
import org.github.scalabletaskmanager.user.sql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(RegisterUserDTO registerUser) {
        if (userRepository.findByUsername(registerUser.getUsername()) != null) {
            throw new UserAlreadyExistsException("User already exists!");
        }
        UserEntity user = new UserEntity();

        user.setUsername(registerUser.getUsername());
        user.setPassword(Hashing.sha256().hashString(registerUser.getPassword(), StandardCharsets.UTF_8).toString());

        userRepository.save(user);
        log.info("User {} registered successfully!", registerUser.getUsername());
    }

    @Override
    public boolean login(String username, String password) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }

    public void updatePassword(String username, String password) {
    }
}
