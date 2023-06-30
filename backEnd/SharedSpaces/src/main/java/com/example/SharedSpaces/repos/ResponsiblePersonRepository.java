package com.example.SharedSpaces.repos;

import com.example.SharedSpaces.models.ResponsiblePerson;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// This interface provides CRUD operations for the ResponsiblePerson entity.
public interface ResponsiblePersonRepository extends CrudRepository<ResponsiblePerson, Long> {

    // Finds a ResponsiblePerson entity by email.
    Optional<ResponsiblePerson> findByEmail(String email);

}