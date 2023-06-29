package com.example.SharedSpaces.repos;

import com.example.SharedSpaces.models.ResponsiblePerson;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ResponsiblePersonRepository extends CrudRepository<ResponsiblePerson, Long> {
    Optional<ResponsiblePerson> findByEmail(String email);

}
