package com.krishna.project.HouZing.dto;

import com.krishna.project.HouZing.entity.type.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {
    private Long id;
    private String username;
    private UserRole userRole;
}
