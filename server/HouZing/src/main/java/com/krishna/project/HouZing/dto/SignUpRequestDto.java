package com.krishna.project.HouZing.dto;

import com.krishna.project.HouZing.entity.type.UserRole;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class SignUpRequestDto {
    private String username;
    private String password;
    private Set<UserRole> roles = new HashSet<>();
    //role

    private Long houseNo;
    private Long phoneNo;
}
