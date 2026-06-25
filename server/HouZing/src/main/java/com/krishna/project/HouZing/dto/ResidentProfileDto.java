package com.krishna.project.HouZing.dto;

import com.krishna.project.HouZing.entity.HUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResidentProfileDto {

    private String username;

    @Column(nullable = false)
    private Long houseNo;

    @Column(nullable = false)
    private Long phoneNo;

    @Column(nullable = false)
    private Boolean isActive = false;
}
