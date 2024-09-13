package org.github.scalabletaskmanager.user.controller;

import org.github.scalabletaskmanager.user.gen.api.UpdateAPI;
import org.github.scalabletaskmanager.user.gen.model.UpdateUserDTO;
import org.github.scalabletaskmanager.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class UpdateController implements UpdateAPI {

    private final UserService userService;

    @Autowired
    public UpdateController(UserService userService) {
        this.userService = userService;
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
