
package com.instep.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.instep.entity.PasswordResetToken;
import com.instep.entity.RegisterApprovalToken;
import com.instep.repository.PasswordResetTokenRepository;
import com.instep.repository.RegisterAprovalTokenRepository;
import com.instep.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ForgotController {

	private final PasswordResetTokenRepository resetTokenRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RegisterAprovalTokenRepository registerAprovalRepo;

	@Value("${FRONTEND}")
	private String FRONTEND;

	@Value("${BACKEND}")
	private String BACKEND;

	// TODO
	@GetMapping("/forgot/page")
	public String changePasswordPage(@RequestParam(required = false) String token, Model model) {

		model.addAttribute("frontend", FRONTEND);
		model.addAttribute("backend", BACKEND);

		if (token == null || token.isBlank()) {
			model.addAttribute("error", "This password reset link is invalid or no longer available.");
			return "back";
		}

		var resetToken = resetTokenRepository.findByToken(token);

		if (resetToken.isEmpty()) {
			model.addAttribute("error", "This password reset link could not be found.");
			return "back";
		}

		var data = resetToken.get();

		if (data.isUsed()) {
			model.addAttribute("error", "This password reset link has already been used.");
			return "back";
		}
		if (data.isDestroyed()) {
			model.addAttribute("error", "This password reset link has been destroyed.");
			return "back";
		}
		if (data.getExpiryAt() == null || data.getExpiryAt().isBefore(LocalDateTime.now())) {
			model.addAttribute("error", "This password reset link has expired.");
			return "back";
		}

		model.addAttribute("token", token);
		return "forgot";
	}

	// TODO
	@GetMapping("/forgot/destroy")
	public String destroy(@RequestParam(required = false) String token, Model model) {

		model.addAttribute("frontend", FRONTEND);
		model.addAttribute("backend", BACKEND);

		if (token == null || token.isBlank()) {
			model.addAttribute("error", "This password reset link is invalid or no longer available.");
			return "back";
		}

		var resetToken = resetTokenRepository.findByToken(token);

		if (resetToken.isEmpty()) {
			model.addAttribute("error", "This password reset link could not be found.");
			return "back";
		}

		var data = resetToken.get();

		if (data.isUsed()) {
			model.addAttribute("error", "This password reset link has already been used.");
			return "back";
		}
		if (data.isDestroyed()) {
			model.addAttribute("error", "This password reset link has already been destroyed.");
			return "back";
		}
		if (data.getExpiryAt() == null || data.getExpiryAt().isBefore(LocalDateTime.now())) {
			model.addAttribute("error", "This password reset link has already expired.");
			return "back";
		}

		data.setDestroyed(true);
		resetTokenRepository.save(data);

		model.addAttribute("msg", "Password reset link destroyed successfully.");
		return "success";
	}

	// TODO
	@PostMapping("/forgot/change")
	public String changePassword(String token, String password, String rePassword, Model model) {
		System.out.println("comming---------------");

		model.addAttribute("frontend", FRONTEND);
		model.addAttribute("backend", BACKEND);

		if (token == null || token.isBlank()) {
			model.addAttribute("error", "Invalid password reset link.");
			return "back";
		}
		if (password == null || password.length() < 5) {
			model.addAttribute("error", "Password must contain at least 5 characters.");
			return "back";
		}
		if (!password.equals(rePassword)) {
			model.addAttribute("error", "Passwords do not match.");
			return "back";
		}

		var resetToken = resetTokenRepository.findByToken(token);

		if (resetToken.isEmpty()) {
			model.addAttribute("error", "This password reset link could not be found.");
			return "back";
		}

		PasswordResetToken data = resetToken.get();

		if (data.isUsed()) {
			model.addAttribute("error", "This password reset link has already been used.");
			return "back";
		}

		if (data.isDestroyed()) {
			model.addAttribute("error", "This password reset link has been destroyed.");
			return "back";
		}

		if (data.getExpiryAt() == null || data.getExpiryAt().isBefore(LocalDateTime.now())) {
			model.addAttribute("error", "This password reset link has expired.");
			return "back";
		}
		System.out.println("pass---------------");
		var user = data.getUser();

		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
		data.setUsed(true);
		resetTokenRepository.save(data);
		System.out.println("return---------------");
		model.addAttribute("msg", "Password changed successfully.");
		return "success";
	}

	// TODO approval while creating account
	@GetMapping("/auth/aproval")
	public String approval(@RequestParam boolean aproval, @RequestParam String token, Model model) {

		if (token == null || token.isBlank()) {
			model.addAttribute("success", false);
			model.addAttribute("message", "Invalid approval request. Please submit the registration request again.");
			return "approval-feedback";
		}

		Optional<RegisterApprovalToken> opt = registerAprovalRepo.findByToken(token);

		if (opt.isEmpty()) {
			model.addAttribute("success", false);
			model.addAttribute("message", "Invalid approval request. Please submit the registration request again.");
			return "approval-feedback";
		}

		RegisterApprovalToken registerToken = opt.get();

		if (registerToken.getUsed()) {
			model.addAttribute("success", false);
			model.addAttribute("message", "This approval request has already been processed.");
			return "approval-feedback";
		}

		if (registerToken.getExpiryAt().isBefore(LocalDateTime.now())) {
			registerToken.setUsed(true);
			registerAprovalRepo.save(registerToken);

			model.addAttribute("success", false);
			model.addAttribute("message", "Your approval request has expired. Please submit the registration request again.");
			return "approval-feedback";
		}

		registerToken.setAproved(aproval);
		registerToken.setUsed(true);
		registerAprovalRepo.save(registerToken);

		model.addAttribute("success", true);
		model.addAttribute("message", aproval
				? "Your registration request has been approved successfully by you."
				: "Your registration request was denied by you. No further action is required.");

		return "approval-feedback";
	}

}
