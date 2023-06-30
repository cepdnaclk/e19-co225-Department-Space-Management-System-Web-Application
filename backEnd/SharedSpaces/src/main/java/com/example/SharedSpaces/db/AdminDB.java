package com.example.SharedSpaces.db;

import com.example.SharedSpaces.models.Admin;
import com.example.SharedSpaces.repos.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// The @Service annotation indicates that this class is a Spring service
@Service
public class AdminDB {

    // The AdminRepository used by this class
    private AdminRepository adminRepository;

    // Constructor for creating a new AdminDB object
    @Autowired
    public AdminDB(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // Returns a List of all Admin objects in the database
    public List<Admin> getAllAdmins() {
        return (List<Admin>) adminRepository.findAll();
    }

    // Returns an Optional of an Admin object with the provided id
    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    // Returns an Optional of an Admin object with the provided email
    public Optional<Admin> getAdminByEmail(String email) {
        if (email == null) {
            // If the email is null, return an empty optional
            return Optional.empty();
        }

        Optional<Admin> optionalAdmin = adminRepository.findByEmail(email);

        if (!optionalAdmin.isPresent()) {
            // If the optionalAdmin is not present, return an empty optional
            return Optional.empty();
        }

        return optionalAdmin;
    }

    // Creates a new Admin object in the database and returns the created object
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // Updates an existing Admin object in the database with the provided id and returns the updated object
    public Admin updateAdmin(Long id, Admin admin) {
        admin.setId(id);
        return adminRepository.save(admin);
    }

    // Deletes an Admin object from the database with the provided id
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}

