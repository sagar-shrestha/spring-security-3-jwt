package com.sagar.springSecurity3JWT.security.service;

import com.sagar.springSecurity3JWT.security.entity.Roles;
import com.sagar.springSecurity3JWT.security.entity.UserInfo;
import com.sagar.springSecurity3JWT.security.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        Set<Roles> roles = userInfo.getRoles();
        List<? extends SimpleGrantedAuthority> mylist = roles.stream()
                .map(data -> new SimpleGrantedAuthority("ROLE_" + data.getRole())).toList();
        return User.withUsername(userInfo.getUsername()).password(userInfo.getPassword())
                .authorities(mylist).build();

    }
}
