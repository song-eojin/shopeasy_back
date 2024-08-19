package com.eojin.shopeasy_back.common;


import lombok.Getter;

@Getter
public class ApiResponseDto {
    private final int statusCode;
    private final String statusMessage;

    public ApiResponseDto(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}

