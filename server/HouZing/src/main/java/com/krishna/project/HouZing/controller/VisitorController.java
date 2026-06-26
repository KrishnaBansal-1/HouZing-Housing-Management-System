package com.krishna.project.HouZing.controller;

import com.krishna.project.HouZing.dto.VisitorApproveDto;
import com.krishna.project.HouZing.dto.VisitorProfileDto;
import com.krishna.project.HouZing.dto.VisitorRequestDto;
import com.krishna.project.HouZing.dto.VisitorResponseDto;
import com.krishna.project.HouZing.entity.HUser;
import com.krishna.project.HouZing.entity.type.VisitorStatus;
import com.krishna.project.HouZing.service.ResidentsService;
import com.krishna.project.HouZing.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/visitor/")
@RequiredArgsConstructor
public class VisitorController {

    private final VisitorService visitorService;
    private final ResidentsService residentsService;

    //for security guards only
    @PostMapping("/register")
    public ResponseEntity<VisitorResponseDto> registerNewVisitor(@RequestBody VisitorRequestDto visitorRequestDto) {
        visitorRequestDto.setVisitorStatus(VisitorStatus.PENDING);
        visitorRequestDto.setEntryTime(LocalDateTime.now());
        return ResponseEntity.ok(
                visitorService.registerVisitor(visitorRequestDto)
        );
    }

    @PutMapping("/visitor-exit/{id}")
    public ResponseEntity<VisitorResponseDto> visitorExit(@PathVariable Long id){
        return ResponseEntity.ok(
                visitorService.visitorExit(id)
        );
    }


    @GetMapping("/all")
    public ResponseEntity<List<VisitorProfileDto>> getAllVisitors() {
        return ResponseEntity.ok(
                visitorService.findAll()
        );
    }

    //for residents
    @PutMapping("/approve")
    public ResponseEntity<VisitorResponseDto> approveVisitor(@RequestBody VisitorApproveDto visitorApproveDto) {
        HUser user = (HUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) throw new IllegalArgumentException("User Not found");

        if (visitorService.findById(visitorApproveDto.getVisitorTokenNo()).getHouseNo() != residentsService.findById(user.getId()).getHouseNo()) {
            throw new IllegalArgumentException("You are not authorized to approve this visitor");
        }

        return ResponseEntity.ok(
                visitorService.approveVisitor(visitorApproveDto)
        );
    }
    //for residents
    @PostMapping("/preregister")
    public ResponseEntity<VisitorResponseDto> preRegisterVisitor(@RequestBody VisitorRequestDto visitorRequestDto) {

        HUser user = (HUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) throw new IllegalArgumentException("User Not found");

        visitorRequestDto.setHouseNo(residentsService.findById(user.getId()).getHouseNo());

        if (visitorRequestDto.getPurposeOfVisit() == null || visitorRequestDto.getPurposeOfVisit().isEmpty()) {
            visitorRequestDto.setPurposeOfVisit("Pre-Registered by " + user.getUsername());
        }

        visitorRequestDto.setVisitorStatus(VisitorStatus.PREAPPROVED);

        //ask user for entry time (estimated)
        if (visitorRequestDto.getEntryTime() == null) {
            visitorRequestDto.setEntryTime(LocalDateTime.now());
        }

        return ResponseEntity.ok(
                visitorService.registerVisitor(visitorRequestDto)
        );
    }

    @GetMapping("/my")
    public ResponseEntity<List<VisitorProfileDto>> getMyVisitors() {
        HUser user = (HUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) throw new IllegalArgumentException("User Not found");

        Long houseNo = residentsService.findById(user.getId()).getHouseNo();

        return ResponseEntity.ok(
                visitorService.findByHouseNo(houseNo)
        );
    }

}
