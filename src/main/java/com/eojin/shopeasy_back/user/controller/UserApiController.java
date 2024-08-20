package com.eojin.shopeasy_back.user.controller;

import com.eojin.shopeasy_back.common.dto.ApiResponseDto;
import com.eojin.shopeasy_back.user.dto.*;
import com.eojin.shopeasy_back.user.entity.User;
import com.eojin.shopeasy_back.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserApiController {
    @Autowired
    UserService userService;

    //관리자모드-회원리스트조회
    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //마이페이지-회원정보조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserResponseDto(user));
    }

    // 마이페이지-회원정보수정
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponseDto> updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserRequestDto requestDto) {
        userService.update(id, requestDto);
        return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.OK.value(), "SUCCESS : 회원정보 수정"));
    }

    //회원탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.OK.value(), "SUCCESS : 회원정보 삭제"));
    }

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto> createUser(@RequestBody CreateUserRequestDto requestDto) {
        userService.create(requestDto);
        return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.CREATED.value(), "SUCCESS : 회원가입"));
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            return ResponseEntity.ok(new LoginResponseDto("로그인 성공"));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDto("로그인 실패"));
        }
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}