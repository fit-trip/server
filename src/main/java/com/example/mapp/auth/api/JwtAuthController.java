package com.example.mapp.auth.api;

import com.example.mapp.auth.dto.TokenDto;
import com.example.mapp.auth.service.JwtAuthService;
import com.example.mapp.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class JwtAuthController {

    private final JwtAuthService jwtAuthService;

    @PostMapping
    public TokenDto login(@RequestBody UserDto userDto) {
        return jwtAuthService.login(userDto);
    }
}
