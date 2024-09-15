package org.github.scalabletaskmanager.user.controller;

import org.github.scalabletaskmanager.user.gen.api.AuthAPI;
import org.github.scalabletaskmanager.user.gen.model.LoginUserDTO;
import org.github.scalabletaskmanager.user.gen.model.RegisterUserDTO;
import org.github.scalabletaskmanager.user.gen.model.UpdateUserDTO;
import org.github.scalabletaskmanager.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String header = attr.getRequest().getHeader("Authorization");
        String jwt = header.substring(7);

        userService.updatePassword(jwt, updateUserDTO);
        return ResponseEntity.ok().build();
    }
}
