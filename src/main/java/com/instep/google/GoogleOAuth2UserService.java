package com.instep.google;

import java.util.Optional;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.instep.entity.User;
import com.instep.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoogleOAuth2UserService extends OidcUserService {

    private final UserRepository userRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) {

        OidcUser oidcUser = super.loadUser(userRequest);

        String email = oidcUser.getEmail();
        String name = oidcUser.getFullName();
        String picture = oidcUser.getPicture();

        Optional<User> existingUser = userRepository.findByEmail(email);

        User user;

        if (existingUser.isPresent()) {

            user = existingUser.get();

            if (name != null && !name.isBlank()) {
                user.setName(name);
            }

            if (picture != null && !picture.isBlank()) {
                user.setProfilePicUrl(picture);
                user.setProfilePicType("URL");
            }

            user.setProvider("GOOGLE");
            userRepository.save(user);

        } else {

            user = new User();
            user.setEmail(email);

            user.setName(
                    name != null && !name.isBlank()
                            ? name
                            : email.substring(0, email.indexOf("@"))
            );

            user.setUsername(createUsername(email));
            user.setPassword("");
            user.setRole("USER");
            user.setEnabled(true);
            user.setProvider("GOOGLE");
            user.setProfilePicUrl(picture);
            user.setProfilePicType("URL");

            userRepository.save(user);
        }

        return oidcUser;
    }

    private String createUsername(String email) {

        String baseUsername = email
                .substring(0, email.indexOf("@"))
                .replaceAll("[^a-zA-Z0-9_]", "");

        if (baseUsername.isBlank()) {
            baseUsername = "user";
        }

        String username = baseUsername;

        int count = 1;

        while (userRepository.existsByUsername(username)) {
            username = baseUsername + count;
            count++;
        }

        return username;
    }
}