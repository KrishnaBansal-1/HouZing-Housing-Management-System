package com.krishna.project.HouZing.controller;


import com.krishna.project.HouZing.dto.ResidentProfileDto;
import com.krishna.project.HouZing.entity.HUser;
import com.krishna.project.HouZing.service.ResidentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/residents/")
@RequiredArgsConstructor
public class ResidentsController {

    private final ResidentsService residentsService;

    @GetMapping("/profile")
    public ResponseEntity<ResidentProfileDto> getProfile() {
        HUser user = (HUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) throw new IllegalArgumentException("User Not found");
        Long residentId = user.getId();
        // Populate profileDto with resident information
        return ResponseEntity.ok(
                residentsService.findById(residentId)
        );
    }
}
