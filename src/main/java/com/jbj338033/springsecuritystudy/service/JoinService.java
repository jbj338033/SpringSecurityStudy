package com.jbj338033.springsecuritystudy.service;

import com.jbj338033.springsecuritystudy.dto.JoinDto;
import com.jbj338033.springsecuritystudy.entity.User;
import com.jbj338033.springsecuritystudy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void join(JoinDto dto) {
        boolean exists = userRepository.existsByUsername(dto.getUsername());

        if (exists) {
            return;
        }

        User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole("ROLE_USER");

        userRepository.save(user);
    }
}
