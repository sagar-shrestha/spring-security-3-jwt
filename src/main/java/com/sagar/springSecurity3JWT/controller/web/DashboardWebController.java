package com.sagar.springSecurity3JWT.controller.web;


import com.sagar.springSecurity3JWT.common.annotation.CustomWebController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@CustomWebController
@RequiredArgsConstructor
public class DashboardWebController {

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @GetMapping("/home")
    public String dashboard(Model model) {
        return "dashboard";
    }
}
