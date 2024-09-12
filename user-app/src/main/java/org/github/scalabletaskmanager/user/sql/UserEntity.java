package org.github.scalabletaskmanager.user.sql;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users", schema = "public")
@Data
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "fullName")
    private String fullName;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    public @NotNull String getFullName() {
        return fullName;
    }

    public @NotNull String getUsername() {
        return username;
    }

    public @NotNull String getPassword() {
        return password;
    }

    @Override @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
