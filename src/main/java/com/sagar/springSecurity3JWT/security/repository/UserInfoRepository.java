package com.sagar.springSecurity3JWT.security.repository;

import com.sagar.springSecurity3JWT.security.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {


    UserInfo findByUsername(String username);
}
