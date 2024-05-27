package com.sagar.springSecurity3JWT.security.service;

import com.sagar.springSecurity3JWT.security.entity.UserInfo;
import com.sagar.springSecurity3JWT.security.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService{


    private final UserInfoRepository userInfoRepository;

    @Override
    public void saveUserInfo(UserInfo userInfo) {

        userInfoRepository.save(userInfo);
    }
}
