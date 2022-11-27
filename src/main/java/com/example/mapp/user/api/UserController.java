package com.example.mapp.user.api;

import com.example.mapp.user.dto.UserDto;
import com.example.mapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public void addUser(@RequestBody UserDto dto) {
        userService.addUser(dto);
    }

    @GetMapping
    public UserDto getUser(@RequestParam String userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // userId
        String principal = (String)authentication.getPrincipal();

        return userService.getUser(userId, principal);
    }
}
