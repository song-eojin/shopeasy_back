package com.eojin.shopeasy_back.user.service;

import com.eojin.shopeasy_back.user.entity.User;
import com.eojin.shopeasy_back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
                       .orElseThrow(() -> new IllegalArgumentException(username));
    }
}
