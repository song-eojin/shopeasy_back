package com.eojin.shopeasy_back.user.dto;

import com.eojin.shopeasy_back.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class CreateUserRequestDto {
    private String username;
    private String nickname;
    private String email;
    private String password;

public User toEntity() {
        return User.builder()
                       .username(username)
                       .nickname(nickname)
                       .email(email)
                       .password(password)
                       .build();
    }
}
