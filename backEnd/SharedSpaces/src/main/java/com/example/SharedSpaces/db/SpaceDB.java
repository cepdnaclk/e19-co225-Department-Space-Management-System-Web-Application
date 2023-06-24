package com.example.SharedSpaces.db;

import com.example.SharedSpaces.models.Space;
import com.example.SharedSpaces.repos.SpaceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpaceDB {


    private SpaceRepository spaceRepository;

    @Autowired
    public SpaceDB(SpaceRepository spaceRepository){
        this.spaceRepository = spaceRepository;
    }

    public List<Space> getAllSpacess() {

        return spaceRepository.findAll();
    }

    public Optional<Space> getSpaceById(Long id) {
        return spaceRepository.findById(id);
    }

    public void addSpace(Space space) {
        spaceRepository.save(space);
    }

    public void updateSpace(Long id, Space space) {
        Optional<Space> optionalSpace = spaceRepository.findById(id);
        if (optionalSpace.isPresent()) {
            Space existingSpace = optionalSpace.get();
            existingSpace.setName(space.getName());
            existingSpace.setDescription(space.getDescription());
            spaceRepository.save(existingSpace);
        }
    }

    public void deleteSpace(Long id, Space space) {
        spaceRepository.deleteById(id);
    }
}
