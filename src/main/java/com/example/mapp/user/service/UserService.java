package com.example.mapp.user.service;

import com.example.mapp.user.dto.UserDto;
import com.example.mapp.user.model.AppUser;
import com.example.mapp.user.model.Role;
import com.example.mapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
