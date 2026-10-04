package com.instep.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instep.entity.PasswordResetToken;
import com.instep.entity.User;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
	Optional<PasswordResetToken> findByUser(User user);
	Optional<PasswordResetToken> findByToken(String token);

}
