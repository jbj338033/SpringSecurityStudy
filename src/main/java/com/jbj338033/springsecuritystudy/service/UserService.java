package com.jbj338033.springsecuritystudy.service;

import com.jbj338033.springsecuritystudy.dto.UserDetailsDto;
import com.jbj338033.springsecuritystudy.entity.User;
import com.jbj338033.springsecuritystudy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        return user.map(UserDetailsDto::new).orElse(null);

    }
}
