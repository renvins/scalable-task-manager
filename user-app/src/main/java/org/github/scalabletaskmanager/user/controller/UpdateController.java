package org.github.scalabletaskmanager.user.controller;

import org.github.scalabletaskmanager.gen.api.UpdateAPI;
import org.github.scalabletaskmanager.gen.model.UpdateUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class UpdateController implements UpdateAPI {

    @Override
    public ResponseEntity<Void> updateUser(UpdateUserDTO updateUserDTO) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        return ResponseEntity.ok().build();
    }
}
