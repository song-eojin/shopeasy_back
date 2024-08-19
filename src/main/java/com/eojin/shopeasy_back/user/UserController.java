package com.eojin.shopeasy_back.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    //회원가입
    @PostMapping()
    public ResponseEntity<ApiResponseDto> createUser(@RequestBody CreateUserRequestDto requestDto) {
        userService.create(requestDto);
        return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.CREATED.value(), "SUCCESS : 회원가입"));
    }

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
}