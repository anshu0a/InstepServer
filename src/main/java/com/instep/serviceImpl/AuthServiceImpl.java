package com.instep.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.instep.config.JwtUtils;
import com.instep.dao.req.LoginRequest;
import com.instep.dao.req.RegisterAprovalRequest;
import com.instep.dao.req.RegisterRequest;
import com.instep.dao.res.ApiResponse;
import com.instep.dao.res.LoginResponse;
import com.instep.dao.res.TokenResponse;
import com.instep.dao.res.UserResponse;
import com.instep.entity.PasswordResetToken;
import com.instep.entity.RegisterApprovalToken;
import com.instep.entity.User;
import com.instep.mail.MailService;
import com.instep.mail.MailString;
import com.instep.repository.PasswordResetTokenRepository;
import com.instep.repository.RegisterAprovalTokenRepository;
import com.instep.repository.UserRepository;
import com.instep.service.AuthService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	@Value("${jwt.access-exp}")
	private Long accessTym;
	@Value("${FRONTEND}")
	private String FRONTEND;
	@Value("${BACKEND}")
	private String BACKEND;

	private final UserRepository userRepo;
	private final UserDetailsService userDetailsservice;
	private final JwtUtils jwtUtils;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder encoder;
	private final PasswordResetTokenRepository passwordResetTokenRepository;
	private final MailService mainService;
	private final RegisterAprovalTokenRepository registerAprovalRepo;

	// TODO login
	@Override
	public LoginResponse login(LoginRequest req) {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
		UserResponse user = toResponse(req.getUsername(), null);
		String accessToken = jwtUtils.generateAccessToken(user.getUsername(), user.getId());
		String refresToken = jwtUtils.generateRefreshToken(user.getUsername(), user.getId());

		return LoginResponse.builder().user(user).accessToken(accessToken).tokenType("Bearer").refreshToken(refresToken)
				.expiresIn(accessTym).success(true).message("Login Successful").build();
	}

	// TODO register
	@Override
	public LoginResponse register(RegisterRequest req) {

		if (userRepo.existsByUsername(req.getUsername()))
			throw new RuntimeException("Username allready exist.");
		else if (userRepo.existsByEmail(req.getEmail()))
			throw new RuntimeException("Email allready exist.");
		else if (req.getPassword().length() <= 4)
			throw new RuntimeException("Password length must be more then 4 character.");
		else if (!req.getConfirmPassword().equals(req.getPassword()))
			throw new RuntimeException("Password not matched.");

		User u = User.builder().name(req.getName()).username(req.getUsername()).email(req.getEmail())
				.password(encoder.encode(req.getPassword())).build();
		u = userRepo.save(u);

		UserResponse user = toResponse(null, u);
		String accessToken = jwtUtils.generateAccessToken(user.getUsername(), user.getId());
		String refresToken = jwtUtils.generateRefreshToken(user.getUsername(), user.getId());

		return LoginResponse.builder().user(user).accessToken(accessToken).refreshToken(refresToken).tokenType("Bearer")
				.expiresIn(accessTym).success(true).message("Register Successful").build();
	}

//	 TODO getToken
	@Override
	public TokenResponse getToken(String token) {
		String newToken = jwtUtils.generateNewAccessToken(token);
		return TokenResponse.builder().success(true).accessToken(newToken).build();
	}

//	 TODO forgot
	@Override
	public ApiResponse forgot(String username) {

		User user = username.contains("@") ? userRepo.findByEmail(username).orElse(null)
				: userRepo.findByUsername(username).orElse(null);

		if (user == null) {
			return ApiResponse.builder().success(false).code(HttpStatus.BAD_REQUEST).message("User not found.").build();
		}

		String email = user.getEmail();

		String token = UUID.randomUUID().toString();

		PasswordResetToken resetToken = passwordResetTokenRepository.findByUser(user).orElse(null);

		if (resetToken == null) {
			resetToken = new PasswordResetToken();
			resetToken.setUser(user);
		}

		resetToken.setToken(token);
		resetToken.setExpiryAt(LocalDateTime.now().plusMinutes(7));
		resetToken.setUsed(false);
		resetToken.setDestroyed(false);
		passwordResetTokenRepository.save(resetToken);

		String resetLink = BACKEND + "/forgot/page?token=" + token;
		String stopResetLink = BACKEND + "/forgot/destroy?token=" + token;

		String expiryTime = resetToken.getExpiryAt().format(DateTimeFormatter.ofPattern("dd MMM yyyy · hh:mm a"));
		String htmlBody = MailString.passwordReset(user.getUsername(), resetLink, expiryTime, stopResetLink);

		try {
			mainService.sendMail(email, "Password Reset Link", htmlBody);
		} catch (MessagingException e) {
			throw new RuntimeException(e.getMessage());
		}

		return ApiResponse.builder().success(true).code(HttpStatus.OK)
				.message("Password reset link sent to your email " + maskEmail(email)).build();
	}

//  TODO
	@Override
	public ApiResponse getApproval(RegisterAprovalRequest req) {
		String email = req.getEmail();
		String token = UUID.randomUUID().toString();

		RegisterApprovalToken aprovalEntity = registerAprovalRepo.findByEmail(email)
				.orElse(new RegisterApprovalToken(email));
		aprovalEntity.setToken(token);
		aprovalEntity.setAproved(false);
		aprovalEntity.setUsed(false);
		aprovalEntity.setExpiryAt(LocalDateTime.now().plusMinutes(7));

		registerAprovalRepo.save(aprovalEntity);

		String aproveLink = BACKEND + "/auth/aproval?token=" + token + "&aproval=true";
		String deniedLink = BACKEND + "/auth/aproval?token=" + token + "&aproval=false";
		String expiryTime = aprovalEntity.getExpiryAt().format(DateTimeFormatter.ofPattern("dd MMM yyyy · hh:mm a"));

		String htmlBody = MailString.registrationApproval(req.getName(), req.getUsername(), email, aproveLink,
				deniedLink, expiryTime);

		try {
			mainService.sendMail(email, "Registation Aproval Link", htmlBody);
		} catch (MessagingException e) {
			throw new RuntimeException(e.getMessage());
		}

		return ApiResponse.builder().success(true).code(HttpStatus.OK)
				.message("We have send aproval request on you given gmail " + maskEmail(email)).build();
	}

// TODO check register request approved or not
	@Override
	public ApiResponse checkApproval(String email) {

		Optional<RegisterApprovalToken> opt = registerAprovalRepo.findByEmail(email);
		
		if (opt.isEmpty())return ApiResponse.builder().success(false).code(HttpStatus.NOT_FOUND).message("No approval request found.").build();

		RegisterApprovalToken approval = opt.get();

		if (!approval.getUsed())return ApiResponse.builder().success(false).code(HttpStatus.OK).message("You have not approved yet.").build();
		if (!approval.getAproved())return ApiResponse.builder().success(false).code(HttpStatus.OK).message("You have denied the register request by your email.").build();
		return ApiResponse.builder().success(true).code(HttpStatus.OK).message("Your registration request has been approved.").build();
	}

//	--------------------------- TODO Helper -----------------------------
//   TODO maskMail
	private String maskEmail(String email) {
		String[] parts = email.split("@", 2);
		String username = parts[0];
		String domain = parts[1];

		if (username.length() <= 3)
			return username.charAt(0) + "***@" + domain;
		return username.substring(0, 3) + "***" + username.substring(username.length() - 2) + "@" + domain;
	}

//	--------------------------- TODO Mapper -----------------------------

	UserResponse toResponse(String username, User u) {
		if (u == null)
			u = (User) userDetailsservice.loadUserByUsername(username);
		return UserResponse.builder()
				.id(u.getId())
				.name(u.getName())
				.username(u.getUsername())
				.birthday(u.getBirthday())
				.gender(u.getGender())
				.profilePicType(u.getProfilePicType())
				.profilePicUrl(u.getProfilePicUrl())
				.profilePicType(u.getProfilePicType())
				.profilePicByte(u.getProfilePicByte())
				.build();
	}

}
