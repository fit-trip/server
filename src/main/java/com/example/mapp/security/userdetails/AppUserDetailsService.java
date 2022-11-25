package com.example.mapp.security.userdetails;

import com.example.mapp.user.model.AppUser;
import com.example.mapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("계정 없음"));
        return new AppUserDetails(appUser);
    }
}
