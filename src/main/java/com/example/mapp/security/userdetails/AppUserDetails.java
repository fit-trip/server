package com.example.mapp.security.userdetails;

import com.example.mapp.user.model.AppUser;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class AppUserDetails extends User {
    public AppUserDetails(AppUser user) {
        super(user.getName(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
    }
}
