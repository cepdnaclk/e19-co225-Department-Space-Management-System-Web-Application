package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.service.AdminService;
import com.example.SharedSpaces.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminController {

    private final AdminService adminService;
    private final EmailService emailService;

    @Autowired
    public AdminController(AdminService adminService, EmailService emailService) {
        this.adminService = adminService;
        this.emailService = emailService;
    }

    @GetMapping
    public void getAllSpaces() {
        emailService.sendEmail("e19372@eng.pdn.ac.lk", "hii", "hello");
    }

}
