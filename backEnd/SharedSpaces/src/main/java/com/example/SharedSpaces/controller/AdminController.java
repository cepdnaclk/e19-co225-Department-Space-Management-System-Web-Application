package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.service.AdminService;
import com.example.SharedSpaces.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminController {

    private final AdminService adminService;
    private final EmailServiceImpl emailServicel;

    @Autowired
    public AdminController(AdminService adminService, EmailServiceImpl emailServicel) {
        this.adminService = adminService;
        this.emailServicel = emailServicel;
    }

    @GetMapping
    public void getAllSpaces() {
        emailServicel.sendEmail("e19372@eng.pdn.ac.lk", "hii", "hello");
    }

}
