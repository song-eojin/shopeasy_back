package com.eojin.shopeasy_back.user;

import lombok.Getter;

@Getter
public class UserResponseDto {
private final Long member_id;
private final String username;
private final String nickname;
private final String email;
private final String profile_url;
private final String password;

public UserResponseDto(User user) {
    this.member_id = user.getId();
    this.username = user.getUsername();
    this.nickname = user.getNickname();
    this.email = user.getEmail();
    this.profile_url = user.getProfile_url();
    this.password = user.getPassword();
}
}
