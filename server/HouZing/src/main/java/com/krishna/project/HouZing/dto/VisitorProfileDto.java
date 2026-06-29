package com.krishna.project.HouZing.dto;

import com.krishna.project.HouZing.entity.type.VisitorStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VisitorProfileDto {

    private Long id;

    private String name;

    private String purposeOfVisit;

    private Long phoneNo;

    private Long houseNo;

    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    private VisitorStatus status;
}
