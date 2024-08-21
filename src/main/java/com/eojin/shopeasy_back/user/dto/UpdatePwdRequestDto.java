package com.eojin.shopeasy_back.user.dto;

import com.eojin.shopeasy_back.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePwdRequestDto {
    private String password;

    public User toEntity() {
        return User.builder()
                       .password(password)
                       .build();
    }
}

