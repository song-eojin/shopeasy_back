package com.eojin.shopeasy_back.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private String message;

    public LoginResponseDto(String message) {
        this.message = message;
    }
}
