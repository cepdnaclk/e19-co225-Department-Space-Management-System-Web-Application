package com.example.SharedSpaces.db;

import com.example.SharedSpaces.models.Space;
import com.example.SharedSpaces.repos.SpaceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// The @Service annotation indicates that this class is a Spring service
@Service
public class SpaceDB {

    // The SpaceRepository used by this class
    private SpaceRepository spaceRepository;

    // Constructor for creating a new SpaceDB object
    @Autowired
    public SpaceDB(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    // Returns a List of all Space objects in the database
    public List<Space> getAllSpacess() {
        return spaceRepository.findAll();
    }

    // Returns an Optional of a Space object with the provided id
    public Optional<Space> getSpaceById(Long id) {
        return spaceRepository.findById(id);
    }

    // Adds a new Space object to the database
    public void addSpace(Space space) {
        spaceRepository.save(space);
    }

    // Updates an existing Space object in the database with the provided id
    public void updateSpace(Long id, Space space) {
        Optional<Space> optionalSpace = spaceRepository.findById(id);
        if (optionalSpace.isPresent()) {
            Space existingSpace = optionalSpace.get();
            existingSpace.setName(space.getName());
            existingSpace.setDescription(space.getDescription());
            spaceRepository.save(existingSpace);
        }
    }

    // Deletes a Space object from the database with the provided id
    public void deleteSpace(Long id, Space space) {
        spaceRepository.deleteById(id);
    }
}

