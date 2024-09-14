package org.github.scalabletaskmanager.task.service;

import org.github.scalabletaskmanager.task.gen.model.UserDTO;

public interface UserClient {

    /**
     * Retrieves a user by their username.
     *
     * @param username the username of the user to be retrieved
     * @return a UserDTO object containing user details if found, or null if no user is found with the given username
     */
    UserDTO getUserByUsername(String username);
}
