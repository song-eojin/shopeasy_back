package com.eojin.shopeasy_back.user.service;

import com.eojin.shopeasy_back.user.dto.CreateUserRequestDto;
import com.eojin.shopeasy_back.user.dto.UpdateUserRequestDto;
import com.eojin.shopeasy_back.user.entity.User;
import com.eojin.shopeasy_back.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public void create(CreateUserRequestDto requestDto) {
        userRepository.save(requestDto.toEntity());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                       .orElseThrow(() -> new IllegalArgumentException(userId + "로 된 사용자를 찾을 수 없습니다."));
    }

    public void delete(long user_id) {
        userRepository.deleteById(user_id);
    }

    @Transactional
    public void update(long user_id, UpdateUserRequestDto requestDto) {
        User user = userRepository.findById(user_id)
                            .orElseThrow(() -> new IllegalArgumentException(user_id + "로 된 사용자를 찾을 수 없습니다."));

        if(requestDto.getNickname() != null) {
            user.updateNickname(requestDto.getNickname());
        }
        if(requestDto.getProfile_url() != null) {
            user.updateProfileUrl(requestDto.getProfile_url());
        }
        if(requestDto.getPassword() != null) {
            user.updatePassword(requestDto.getPassword());
        }
    }
    @Transactional
    public void encryptPassword(String password){
        User user = new User();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}
