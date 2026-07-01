package com.krishna.project.HouZing.controller;

import com.krishna.project.HouZing.dto.*;
import com.krishna.project.HouZing.entity.HUser;
import com.krishna.project.HouZing.entity.type.UserRole;
import com.krishna.project.HouZing.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

    @PutMapping("/change-password")
    ResponseEntity<SignUpResponseDto> changePass(@RequestBody ChangePasswordDto changePasswordDto){
        HUser user = (HUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) throw new IllegalArgumentException("User Not found");
        boolean isAdmin = false;
        if (new ArrayList<>(user.getRoles()).getFirst() == UserRole.ADMIN) isAdmin = true;
        return ResponseEntity.ok(
                authService.changePassword(changePasswordDto, user, isAdmin)
        );
    }
}