package com.example.SharedSpaces.repos;

import com.example.SharedSpaces.models.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// This interface provides CRUD operations for the Space entity using JPA.
public interface SpaceRepository extends JpaRepository<Space, Long> {

}