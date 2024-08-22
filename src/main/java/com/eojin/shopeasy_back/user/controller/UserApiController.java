package com.eojin.shopeasy_back.user.controller;

import com.eojin.shopeasy_back.common.dto.ApiResponseDto;
import com.eojin.shopeasy_back.common.security.UserDetailsImpl;
import com.eojin.shopeasy_back.user.dto.*;
import com.eojin.shopeasy_back.user.entity.User;
import com.eojin.shopeasy_back.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
@Log4j2
@Validated
public class UserApiController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto> signup(@RequestBody @Valid SignupRequestDto requestDto, BindingResult bindingResult) {

        // Validation Exception Handling
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "FAIL : 회원가입"));
        }
        userService.create(requestDto);
        return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.CREATED.value(), "SUCCESS : 회원가입"));
    }

    // for MANAGER (UserRole)
    @GetMapping()
    public List<User> getAllUsers(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.getAllUsers();
    }

    //for My Page
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("id") Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserResponseDto(user));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponseDto> updateUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("id") Long id, @RequestBody @Valid UpdatePwdRequestDto requestDto) {
        try {
            userService.updatePassword(id, requestDto);
            return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.OK.value(), "SUCCESS : 회원정보 수정"));
        } catch(RejectedExecutionException e) {
            return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.OK.value(), "FAIL : 회원정보 수정 - 작성자만 수정 가능합니다."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.OK.value(), "FAIL : 회원정보 수정 - 잘못된 인자 값이 전달되었습니다."));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> deleteUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.OK.value(), "SUCCESS : 회원정보 삭제"));
    }
}