package org.github.scalabletaskmanager.user.controller;

import org.github.scalabletaskmanager.common.util.JwtUtil;
import org.github.scalabletaskmanager.user.gen.api.AuthAPI;
import org.github.scalabletaskmanager.user.gen.model.LoginUserDTO;
import org.github.scalabletaskmanager.user.gen.model.RegisterUserDTO;
import org.github.scalabletaskmanager.user.gen.model.UpdateUserDTO;
import org.github.scalabletaskmanager.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthAPI {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<String> loginUser(LoginUserDTO userDTO) {
        String jwt = userService.login(userDTO);
        return ResponseEntity.ok(jwt);
    }

    @Override
    public ResponseEntity<String> registerUser(RegisterUserDTO registerUserDTO) {
        String jwt = userService.register(registerUserDTO);
        return ResponseEntity.ok(jwt);
    }

    @Override
    public ResponseEntity<Void> updateUser(UpdateUserDTO updateUserDTO) {
        String jwt = JwtUtil.getJwtFromRequest();

        userService.updatePassword(jwt, updateUserDTO);
        return ResponseEntity.ok().build();
    }
}
