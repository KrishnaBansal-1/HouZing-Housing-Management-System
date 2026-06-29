package com.krishna.project.HouZing.security;

import com.krishna.project.HouZing.dto.*;
import com.krishna.project.HouZing.entity.HUser;
import com.krishna.project.HouZing.entity.Residents;
import com.krishna.project.HouZing.entity.type.UserRole;
import com.krishna.project.HouZing.repository.HUserRepository;
import com.krishna.project.HouZing.repository.ResidentsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Authenticator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final HUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ResidentsRepository residentsRepository;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        //successful auth
        HUser user = (HUser) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        List<UserRole> userRoleList = user.getRoles().stream().toList();

        return new LoginResponseDto(user.getId(), token, userRoleList.getFirst(), user.getUsername());

    }

    @Transactional
    public SignUpResponseDto signup(SignUpRequestDto signUpRequestDto) {

        HUser user = userRepository.findByUsername(signUpRequestDto.getUsername()).orElse(null);

        if (user != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        user = userRepository.save(
                HUser.builder()
                        .username(signUpRequestDto.getUsername())
                        .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                        .roles(signUpRequestDto.getRoles())
                        .build()
        );

        if (signUpRequestDto.getRoles().contains(UserRole.RESIDENT)) {
            Residents resident = Residents.builder()
                    .user(user)
                    .houseNo(signUpRequestDto.getHouseNo())
                    .phoneNo(signUpRequestDto.getPhoneNo())
                    .isActive(true)
                    .build();
            residentsRepository.save(resident);
        }
        return new SignUpResponseDto(user.getId(), user.getUsername());
    }

    public SignUpResponseDto changePassword(ChangePasswordDto changePasswordDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(changePasswordDto.getUsername(), changePasswordDto.getPassword())
        );

        HUser user = (HUser) authentication.getPrincipal();

        if (user == null){
            throw new IllegalArgumentException("Invalid username or password");
        }

        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        userRepository.save(user);

        return new SignUpResponseDto(user.getId(), user.getUsername());
    }
}
