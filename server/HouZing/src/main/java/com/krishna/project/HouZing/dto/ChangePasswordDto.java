package com.krishna.project.HouZing.dto;

import lombok.Data;

@Data
public class ChangePasswordDto {

    private String username;
    private String password;
    private String newPassword;

}
