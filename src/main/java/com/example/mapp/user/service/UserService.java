package com.example.mapp.user.service;

import com.example.mapp.user.dto.UserDto;
import com.example.mapp.user.model.AppUser;
import com.example.mapp.user.model.Role;
import com.example.mapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void addUser(UserDto dto) {
        AppUser appUser = AppUser.builder()
                .id(dto.getId())
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(appUser);
    }

    public UserDto getUser(String userId, String principal) {
        AppUser appUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("가입되지 않은 ID입니다."));

        if (!Objects.equals(appUser.getId(), principal)) {
            throw new IllegalStateException("다른 사용자의 정보는 조회할 수 없습니다.");
        }

        return UserDto.builder()
                .id(appUser.getId())
                .name(appUser.getName())
                .build();
    }
}
