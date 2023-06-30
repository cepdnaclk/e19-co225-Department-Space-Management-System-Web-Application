package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// The @CrossOrigin annotation enables Cross-Origin Resource Sharing (CORS) for this controller
@CrossOrigin
// The @RestController annotation indicates that this class is a REST controller
@RestController
// The @RequestMapping annotation maps HTTP requests to a specific URL or URL pattern
@RequestMapping("admin")
public class AdminController {

    // The AdminService used by this controller
    private final AdminService adminService;

    // Constructor for creating a new AdminController object
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

}

