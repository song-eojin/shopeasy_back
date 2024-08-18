package com.eojin.shopeasy_back.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDto {
private String nickname;
private String profile_url;
private String password;

public User toEntity() {
    return User.builder()
                   .nickname(nickname)
                   .profile_url(profile_url)
                   .password(password)
                   .build();
}
}

