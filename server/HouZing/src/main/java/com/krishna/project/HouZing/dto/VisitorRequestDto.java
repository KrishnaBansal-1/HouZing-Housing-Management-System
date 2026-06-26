package com.krishna.project.HouZing.dto;

import com.krishna.project.HouZing.entity.type.VisitorStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VisitorRequestDto {

    private String name;

    private String purposeOfVisit;

    private Long phoneNo;

    private Long houseNo;

    private VisitorStatus visitorStatus;

    private LocalDateTime entryTime;
}
