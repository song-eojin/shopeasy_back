package com.eojin.shopeasy_back.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto {
private String username;
private String nickname;
private String email;
private String profile_url;
private String password;

public User toEntity() {
    return User.builder()
                   .username(username)
                   .nickname(nickname)
                   .email(email)
                   .profile_url(profile_url)
                   .password(password)
                   .build();
}
}
