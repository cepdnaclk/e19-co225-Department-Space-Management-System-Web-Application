package com.example.SharedSpaces.db;

import com.example.SharedSpaces.models.Admin;
import com.example.SharedSpaces.repos.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminDB {


    private AdminRepository adminRepository;

    @Autowired
    public AdminDB(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
        return (List<Admin>) adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    public Optional<Admin> getAdminByEmail(String email) {
        if (email == null) {
            return Optional.empty();
        }

        Optional<Admin> optionalAdmin = adminRepository.findByEmail(email);

        if (!optionalAdmin.isPresent()) {

            // In here, we are returning an empty optional
            return Optional.empty();
        }

        return optionalAdmin;
    }


    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long id, Admin admin) {
        admin.setId(id);
        return adminRepository.save(admin);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}

