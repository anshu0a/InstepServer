package com.instep.google;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.instep.config.JwtUtils;
import com.instep.entity.User;
import com.instep.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtUtils jwtService;
    
    @Value("${FRONTEND}")
    private String FRONTEND;
    

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
        String email = oauthUser.getAttribute("email");

        User user =
                userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        String accessToken = jwtService.generateAccessToken(user.getUsername(), user.getId());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername(), user.getId());

        String frontendUrl =FRONTEND + 
                "/oauth-success"
                        + "?accessToken="
                        + URLEncoder.encode(
                                accessToken,
                                StandardCharsets.UTF_8
                        )
                        + "&refreshToken="
                        + URLEncoder.encode(
                                refreshToken,
                                StandardCharsets.UTF_8
                        )
                        + "&expiresIn="
                        + jwtService.getAccessExpiration()
                        + "&username="
                        + URLEncoder.encode(
                                user.getUsername(),
                                StandardCharsets.UTF_8
                        );

        getRedirectStrategy().sendRedirect(
                request,
                response,
                frontendUrl
        );
    }
}