package com.eojin.shopeasy_back.user.dto;

import com.eojin.shopeasy_back.user.entity.User;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String username;
    private String realname;
    private String email;
    private String password;

public User toEntity() {
        return User.builder()
                       .username(username)
                       .realname(realname)
                       .email(email)
                       .password(password)
                       .build();
    }
}
