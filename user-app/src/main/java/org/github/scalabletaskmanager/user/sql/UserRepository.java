package org.github.scalabletaskmanager.user.sql;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Retrieves a user entity based on the provided username.
     *
     * @param username the username of the user to be fetched
     * @return the UserEntity associated with the given username, or null if no such user exists
     */
    UserEntity findByUsername(String username);
}
