package com.example.SharedSpaces.repos;



import com.example.SharedSpaces.models.Space;

import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {

}
