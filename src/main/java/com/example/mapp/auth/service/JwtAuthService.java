package com.example.mapp.auth.service;

import com.example.mapp.auth.dto.TokenDto;
import com.example.mapp.user.dto.UserDto;
import com.example.mapp.user.model.AppUser;
import com.example.mapp.user.repository.UserRepository;
import com.example.mapp.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JwtAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public TokenDto login(UserDto dto) {
        String id = dto.getId();

        AppUser user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("아이디 없음"));

        boolean isPassed = passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if (!isPassed) {
            throw new IllegalStateException("비밀번호 불일치");
        }

        return new TokenDto(user.getName(), jwtUtil.generateAccessToken(id));
    }
}
