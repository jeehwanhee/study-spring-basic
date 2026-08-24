package com.example.demo.domain;

import com.example.demo.exception.BusinessException;
import com.example.demo.exception.UserErrorCode;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name="users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Builder
    public User(String username, String password) {
        validateUsername(username);
        validatePassword(password);
        this.username = username;
        this.password = password;
        this.role = "USER";
    }

    private void validateUsername(String username) {
        if (username == null || username.isBlank())
            throw new BusinessException(UserErrorCode.INVALID_USERNAME);
    }

    private void validatePassword(String password) {
        if (password == null || password.isBlank())
            throw new BusinessException(UserErrorCode.INVALID_PASSWORD);
        }


}
