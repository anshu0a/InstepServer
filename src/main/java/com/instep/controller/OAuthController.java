package com.instep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oauth")
public class OAuthController {

    @GetMapping("/google")
    public String googleLogin() {
        return "redirect:/oauth2/authorization/google";
    }
}