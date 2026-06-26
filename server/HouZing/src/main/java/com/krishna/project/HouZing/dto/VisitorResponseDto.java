package com.krishna.project.HouZing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitorResponseDto {

    private Long visitorTokenNo;
    private String name;
}
