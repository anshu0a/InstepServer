package com.instep.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.instep.google.GoogleOAuth2UserService;
import com.instep.google.OAuth2SuccessHandler;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final GoogleOAuth2UserService googleOAuth2UserService;
	private final OAuth2SuccessHandler oauth2SuccessHandler;

	@Value("${FRONTEND}")
	private String FRONTEND;

	@Bean
	PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain getFilter(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).cors(cors -> cors.configurationSource(request -> {
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowedOrigins(List.of(FRONTEND));
			config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
			config.setAllowedHeaders(List.of("*"));
			config.setAllowCredentials(true);
			return config;
		}))

				.exceptionHandling(ex -> ex.authenticationEntryPoint((request, response, exception) -> {
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
					response.setContentType("application/json");
					response.getWriter().write("""
							{"success": false,"message": "Authentication required","code": 401}""");
				}).accessDeniedHandler((request, response, exception) -> {
					response.setStatus(HttpStatus.FORBIDDEN.value());
					response.setContentType("application/json");
					response.getWriter().write("""
							{ "success": false, "message": "Access denied", "code": 403 } """);
				}))

				.oauth2Login(oauth -> oauth
						.userInfoEndpoint(userInfo -> userInfo
								.oidcUserService(googleOAuth2UserService))
						.successHandler(oauth2SuccessHandler))

				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

				.authorizeHttpRequests(auth -> auth
						.requestMatchers(
								"/auth/ok",
								"/auth/login",
								"/auth/token",
								"/auth/register",
								"/auth/forgot",
								"/auth/forgot/**",
								"/auth/aproval",
								"/auth/checkaproval",

								"/user/{username}",
								"/user/get/{username}",

								"/forgot/**",
								"/WEB-INF/pages/**",
								"/error",

								"/oauth/google",
								"/oauth2/**",
								"/login/**"
						).permitAll()
						.anyRequest().authenticated());

		return http.build();
	}

	@Bean
	AuthenticationManager getManager(UserDetailsService userDtl, PasswordEncoder encoder) {

		DaoAuthenticationProvider dao = new DaoAuthenticationProvider(userDtl);
		dao.setPasswordEncoder(encoder);

		return new ProviderManager(dao);
	}
}