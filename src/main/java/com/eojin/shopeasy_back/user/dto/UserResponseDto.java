package com.eojin.shopeasy_back.user.dto;

import com.eojin.shopeasy_back.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private final Long user_id;
    private final String username;
    private final String nickname;
    private final String email;
    private final String profile_url;
    private final String password;

    public UserResponseDto(User user) {
        this.user_id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.profile_url = user.getProfile_url();
        this.password = user.getPassword();
    }
}
