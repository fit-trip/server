package com.example.mapp.user.api;

import com.example.mapp.user.dto.UserDto;
import com.example.mapp.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "사용자 관리")
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "회원 가입")
    public void addUser(@RequestBody UserDto dto) {
        userService.addUser(dto);
    }
}
