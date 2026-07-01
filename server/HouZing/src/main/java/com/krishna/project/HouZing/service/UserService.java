package com.krishna.project.HouZing.service;

import com.krishna.project.HouZing.dto.UserProfileDto;
import com.krishna.project.HouZing.entity.HUser;
import com.krishna.project.HouZing.repository.HUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final HUserRepository userRepository;


    public List<UserProfileDto> getAllUsers() {
        List<HUser> users = userRepository.findAll();

        return users.stream().map(user -> new UserProfileDto(
                user.getId(),
                user.getUsername(),
                new ArrayList<>(user.getRoles()).getFirst()
        )).toList();
    }
}
