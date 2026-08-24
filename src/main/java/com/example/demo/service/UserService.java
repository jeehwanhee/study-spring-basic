package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.UserErrorCode;
import com.example.demo.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String signup(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new BusinessException(UserErrorCode.DUPLICATE_USERNAME);
        }

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();

        userRepository.save(user);
        return "회원가입 완료";
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BusinessException(UserErrorCode.PASSWORD_MISMATCH);
        }

        return jwtUtil.createToken(user.getUsername());
    }
}
