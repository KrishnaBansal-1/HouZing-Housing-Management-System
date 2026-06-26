package com.krishna.project.HouZing.dto;

import com.krishna.project.HouZing.entity.type.VisitorStatus;
import lombok.Data;

@Data
public class VisitorApproveDto {
    private Long visitorTokenNo;
    private Boolean isApproved;
}
