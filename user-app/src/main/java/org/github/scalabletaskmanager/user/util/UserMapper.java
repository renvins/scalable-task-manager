package org.github.scalabletaskmanager.user.util;

import org.github.scalabletaskmanager.user.gen.model.UserDTO;
import org.github.scalabletaskmanager.user.sql.UserEntity;

public class UserMapper {

    public static UserDTO toDTO(UserEntity user) {
        return new UserDTO(
                user.getId(),
                user.getFullName(),
                user.getUsername());
    }
}
