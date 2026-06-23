package com.krishna.project.HouZing.controller;

import com.krishna.project.HouZing.dto.LoginRequestDto;
import com.krishna.project.HouZing.dto.LoginResponseDto;
import com.krishna.project.HouZing.dto.SignUpRequestDto;
import com.krishna.project.HouZing.dto.SignUpResponseDto;
import com.krishna.project.HouZing.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = authService.login(loginRequestDto);
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/signup")
    ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto signUpResponseDto = authService.signup(signUpRequestDto);
        return ResponseEntity.ok(signUpResponseDto);
    }
}