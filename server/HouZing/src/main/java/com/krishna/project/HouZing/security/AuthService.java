package com.krishna.project.HouZing.security;

import com.krishna.project.HouZing.dto.LoginRequestDto;
import com.krishna.project.HouZing.dto.LoginResponseDto;
import com.krishna.project.HouZing.dto.SignUpRequestDto;
import com.krishna.project.HouZing.dto.SignUpResponseDto;
import com.krishna.project.HouZing.entity.HUser;
import com.krishna.project.HouZing.repository.HUserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Authenticator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final HUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        //successful auth
        HUser user = (HUser) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto(user.getId(), token);

    }

    public SignUpResponseDto signup(SignUpRequestDto signUpRequestDto) {

        HUser user = userRepository.findByUsername(signUpRequestDto.getUsername()).orElse(null);

        if (user != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        user = userRepository.save(
                HUser.builder()
                        .username(signUpRequestDto.getUsername())
                        .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                        .build()
        );
        return new SignUpResponseDto(user.getId(), user.getUsername());
    }
}
