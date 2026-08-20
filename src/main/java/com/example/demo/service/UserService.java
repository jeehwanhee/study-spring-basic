package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
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
            throw new IllegalArgumentException("이미 사용중인 아이디");
        }

        User user = new User();
        user.setUsername(username);

        String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);

        user.setRole("USER");

        userRepository.save(user);
        return "회원가입 완료";
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("미가입 아이디"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        return jwtUtil.createToken(user.getUsername());
    }
}
