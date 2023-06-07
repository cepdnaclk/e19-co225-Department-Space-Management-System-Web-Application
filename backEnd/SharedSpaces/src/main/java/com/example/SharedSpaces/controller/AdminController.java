package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.service.AdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String getInfo(){
        return null;
    }

    @PostMapping
    public void addResevation(){

    }

    @PutMapping()
    public void updateResevation(){

    }

    @DeleteMapping()
    public  void deleteResevation(){

    }
}
