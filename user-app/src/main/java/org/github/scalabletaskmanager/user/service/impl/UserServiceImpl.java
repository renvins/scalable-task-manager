package org.github.scalabletaskmanager.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.github.scalabletaskmanager.common.service.JwtService;
import org.github.scalabletaskmanager.user.exception.UserAlreadyExistsException;
import org.github.scalabletaskmanager.common.exception.UserNotFoundException;
import org.github.scalabletaskmanager.user.gen.model.LoginUserDTO;
import org.github.scalabletaskmanager.user.gen.model.RegisterUserDTO;
import org.github.scalabletaskmanager.user.gen.model.UpdateUserDTO;
import org.github.scalabletaskmanager.user.service.UserService;
import org.github.scalabletaskmanager.user.sql.UserEntity;
import org.github.scalabletaskmanager.user.sql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(JwtService jwtService, UserRepository userRepository,
                           AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;

        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public String register(RegisterUserDTO registerUser) {
        if (userRepository.findByUsername(registerUser.getUsername()) != null) {
            throw new UserAlreadyExistsException("User already exists!", HttpStatus.CONFLICT);
        }
        UserEntity user = new UserEntity();

        user.setFullName(registerUser.getFullName());
        user.setUsername(registerUser.getUsername());
        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));

        userRepository.save(user);

        String jwt = jwtService.generateToken(new HashMap<>(), user.getUsername());
        log.info("User {} registered successfully!", registerUser.getUsername());

        return jwt;
    }

    @Override
    public String login(LoginUserDTO loginUserDTO) {
        /* This will call AuthenticationProvider with UserDetailsService and PasswordEncoder
        * to retrieve the user by username and compare passwords */
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUserDTO.getUsername(), loginUserDTO.getPassword()));

        UserEntity user = getUserByUsername(loginUserDTO.getUsername());
        return jwtService.generateToken(new HashMap<>(), user.getUsername());
    }

    @Override
    public void updatePassword(String jwt, UpdateUserDTO updateUser) {
        String username = jwtService.extractUsername(jwt);
        UserEntity user = getUserByUsername(username);

        String encodedPassword = passwordEncoder.encode(updateUser.getNewPassword());

        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User " + username + " not found!", HttpStatus.NOT_FOUND);
        }
        return user;
    }
}
