package org.github.scalabletaskmanager.user.service;

import org.github.scalabletaskmanager.gen.model.RegisterUserDTO;

public interface UserService {

    /**
     * Registers a new user with the details provided in the RegisterUserDTO.
     *
     * @param registerUser the data transfer object containing user registration information
     */
    void register(RegisterUserDTO registerUser);


    /**
     * Authenticates a user using the provided username and password.
     *
     * @param username the username of the user attempting to log in
     * @param password the password of the user attempting to log in
     * @return true if the login credentials are valid, false otherwise
     */
    boolean login(String username, String password);
}
