package com.krishna.project.HouZing.service;

import com.krishna.project.HouZing.dto.VisitorApproveDto;
import com.krishna.project.HouZing.dto.VisitorProfileDto;
import com.krishna.project.HouZing.dto.VisitorRequestDto;
import com.krishna.project.HouZing.dto.VisitorResponseDto;
import com.krishna.project.HouZing.entity.Visitor;
import com.krishna.project.HouZing.entity.type.VisitorStatus;
import com.krishna.project.HouZing.repository.ResidentsRepository;
import com.krishna.project.HouZing.repository.VisitorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitorService {

    private final VisitorRepository visitorRepository;
    private final ResidentsRepository residentsRepository;

    @Transactional
    public VisitorResponseDto registerVisitor(VisitorRequestDto visitorRequestDto) {

        if (residentsRepository.findByHouseNo(visitorRequestDto.getHouseNo()) == null || residentsRepository.findByHouseNo(visitorRequestDto.getHouseNo()).getIsActive() == false) {
            throw new IllegalArgumentException("House number not found / not active");
        }

        Visitor visitor = visitorRepository.save(
                Visitor.builder()
                        .name(visitorRequestDto.getName())
                        .purposeOfVisit(visitorRequestDto.getPurposeOfVisit())
                        .phoneNo(visitorRequestDto.getPhoneNo())
                        .houseNo(visitorRequestDto.getHouseNo())
                        .entryTime(visitorRequestDto.getEntryTime())
                        .status(visitorRequestDto.getVisitorStatus())
                        .build()
        );

        //add Notify Logic (Notify the house owner to approve the visitor)

        return new VisitorResponseDto(visitor.getId(), visitor.getName());
    }

    public VisitorProfileDto findById(Long visitorTokenNo) {
        Visitor visitor = visitorRepository.findById(visitorTokenNo).orElseThrow(() -> new IllegalArgumentException("Visitor not found"));
        return new VisitorProfileDto(
                visitor.getId(),
                visitor.getName(),
                visitor.getPurposeOfVisit(),
                visitor.getPhoneNo(),
                visitor.getHouseNo(),
                visitor.getEntryTime(),
                visitor.getExitTime(),
                visitor.getStatus()
        );
    }

    @Transactional
    public VisitorResponseDto approveVisitor(VisitorApproveDto visitorApproveDto) {

        Visitor visitor = visitorRepository.findById(visitorApproveDto.getVisitorTokenNo()).orElseThrow(() ->
                new IllegalArgumentException("Visitor not found"));

        visitor.setStatus(visitorApproveDto.getIsApproved() ? VisitorStatus.APPROVED : VisitorStatus.REJECTED);

        visitor = visitorRepository.save(visitor);

        //Notify Security About the approved status

        return new VisitorResponseDto(visitor.getId(), visitor.getName());
    }

    @Transactional
    public VisitorResponseDto visitorExit(Long id) {

        Visitor visitor = visitorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Visitor not found"));

        if (visitor.getStatus() != VisitorStatus.APPROVED && visitor.getStatus() != VisitorStatus.PREAPPROVED) {
            throw new IllegalArgumentException("Visitor is not inside the HouZing");
        }

        visitor.setStatus(VisitorStatus.LEFT);
        visitor.setExitTime(LocalDateTime.now());

        visitor = visitorRepository.save(visitor);

        return new VisitorResponseDto(visitor.getId(), visitor.getName());
    }

    public List<VisitorProfileDto> findByHouseNo(Long houseNo) {
        List<Visitor> visitors = visitorRepository.findByHouseNo(houseNo);
        return visitors.stream().map(visitor -> new VisitorProfileDto(
                visitor.getId(),
                visitor.getName(),
                visitor.getPurposeOfVisit(),
                visitor.getPhoneNo(),
                visitor.getHouseNo(),
                visitor.getEntryTime(),
                visitor.getExitTime(),
                visitor.getStatus()
        )).toList();
    }

    public List<VisitorProfileDto> findAll() {
        List<Visitor> visitors = visitorRepository.findAll();
        return visitors.stream().map(visitor -> new VisitorProfileDto(
                visitor.getId(),
                visitor.getName(),
                visitor.getPurposeOfVisit(),
                visitor.getPhoneNo(),
                visitor.getHouseNo(),
                visitor.getEntryTime(),
                visitor.getExitTime(),
                visitor.getStatus()
        )).toList();
    }
}
