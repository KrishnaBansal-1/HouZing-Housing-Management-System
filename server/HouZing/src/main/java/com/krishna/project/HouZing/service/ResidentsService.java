package com.krishna.project.HouZing.service;

import com.krishna.project.HouZing.dto.ResidentProfileDto;
import com.krishna.project.HouZing.entity.Residents;
import com.krishna.project.HouZing.repository.ResidentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResidentsService {
    
    private final ResidentsRepository residentsRepository;

    public ResidentProfileDto findById(Long residentId) {
        Residents resident = residentsRepository.findById(residentId).orElseThrow(() -> new RuntimeException("Resident not found"));
        return new ResidentProfileDto(
                resident.getUser().getUsername(),
                resident.getHouseNo(),
                resident.getPhoneNo(),
                resident.getIsActive()
        );
    }
}