package com.example.SharedSpaces.repos;

import com.example.SharedSpaces.models.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
// This interface provides CRUD operations for the Admin entity.
public interface AdminRepository extends CrudRepository<Admin, Long> {

    // Finds an Admin entity by email.
    Optional<Admin> findByEmail(String email);
}