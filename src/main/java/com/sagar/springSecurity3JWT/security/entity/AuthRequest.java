package com.sagar.springSecurity3JWT.security.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class AuthRequest {

    private String username;
    private String password;
}
