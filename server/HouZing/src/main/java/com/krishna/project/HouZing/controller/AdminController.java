package com.krishna.project.HouZing.controller;

import com.krishna.project.HouZing.dto.ResidentProfileDto;
import com.krishna.project.HouZing.dto.UserProfileDto;
import com.krishna.project.HouZing.repository.ResidentsRepository;
import com.krishna.project.HouZing.service.ResidentsService;
import com.krishna.project.HouZing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/")
@RequiredArgsConstructor
public class AdminController {

    private final ResidentsService residentService;
    private final UserService userService;

    @GetMapping("/residents")
    public ResponseEntity<List<ResidentProfileDto>> getAllResidents() {

        return ResponseEntity.ok(
                residentService.getAllResidents()
        );
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserProfileDto>> getAllUsers(){

        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }
}
