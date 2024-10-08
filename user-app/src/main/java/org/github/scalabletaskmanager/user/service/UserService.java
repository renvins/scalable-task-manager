package org.github.scalabletaskmanager.user.service;

import org.github.scalabletaskmanager.user.gen.model.LoginUserDTO;
import org.github.scalabletaskmanager.user.gen.model.RegisterUserDTO;
import org.github.scalabletaskmanager.user.gen.model.UpdateUserDTO;
import org.github.scalabletaskmanager.user.sql.UserEntity;

public interface UserService {

    /**
     * Registers a new user with the provided registration details.
     *
     * @param registerUser an instance of RegisterUserDTO containing the user registration details.
     * @return a string representing the result of the registration process.
     */
    String register(RegisterUserDTO registerUser);


    /**
     * Authenticates a user based on the provided login credentials.
     *
     * @param loginUserDTO an instance of LoginUserDTO containing the user's login credentials.
     * @return a string representing the session token or authentication status.
     */
    String login(LoginUserDTO loginUserDTO);


    /**
     * Updates the user's password based on the provided JWT and update information.
     *
     * @param jwt the JSON Web Token used for authentication and authorization.
     * @param updateUser an instance of UpdateUserDTO containing the current and new password information.
     */
    void updatePassword(String jwt, UpdateUserDTO updateUser);

    /**
     * Retrieves a user based on the provided username.
     *
     * @param username the username of the user to retrieve.
     * @return the UserEntity corresponding to the provided username.
     */
    UserEntity getUserByUsername(String username);
}
