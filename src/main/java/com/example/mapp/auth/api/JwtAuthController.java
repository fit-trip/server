package com.example.mapp.auth.api;

import com.example.mapp.auth.dto.TokenDto;
import com.example.mapp.auth.service.JwtAuthService;
import com.example.mapp.user.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "인증 정보(토큰) 관리")
public class JwtAuthController {

    private final JwtAuthService jwtAuthService;

    @PostMapping
    @Operation(summary = "로그인")
    public TokenDto login(@RequestBody UserDto userDto) {
        return jwtAuthService.login(userDto);
    }
}
