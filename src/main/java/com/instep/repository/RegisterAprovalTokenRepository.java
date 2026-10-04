package com.instep.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instep.entity.RegisterApprovalToken;

public interface RegisterAprovalTokenRepository extends JpaRepository<RegisterApprovalToken, Long> {

	
	Optional<RegisterApprovalToken> findByEmail(String email);
	Optional<RegisterApprovalToken> findByToken(String token);
}
