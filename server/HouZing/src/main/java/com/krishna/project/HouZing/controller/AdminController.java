package com.krishna.project.HouZing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/view")
    public String view() {
        return "Admin view";
    }
}
