package com.example.SharedSpaces.controller;

import com.example.SharedSpaces.service.AdminService;
import com.example.SharedSpaces.service.MailchimpServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminController {

    private final AdminService adminService;
    private  final MailchimpServices mailchimpServices;

    @Autowired
    public AdminController(AdminService adminService, MailchimpServices mailchimpServices) {
        this.adminService = adminService;
        this.mailchimpServices = mailchimpServices;
    }

    @GetMapping
    public String getInfo(){
        String[] emails = {"kanishkagunawarthana@gmail.com"};
        mailchimpServices.sendEmail(emails, "Hii", "hello");
        return null;
    }
//
//    @PostMapping
//    public void addResevation(){
//
//    }
//
//    @PutMapping()
//    public void updateResevation(){
//
//    }
//
//    @DeleteMapping()
//    public  void deleteResevation(){
//
//    }
}
