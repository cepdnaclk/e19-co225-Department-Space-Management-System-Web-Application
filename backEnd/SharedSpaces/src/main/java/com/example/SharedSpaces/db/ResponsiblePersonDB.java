package com.example.SharedSpaces.db;

import com.example.SharedSpaces.models.ResponsiblePerson;
import com.example.SharedSpaces.repos.ResponsiblePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// The @Service annotation indicates that this class is a Spring service
@Service
public class ResponsiblePersonDB {

    // The ResponsiblePersonRepository used by this class
    private ResponsiblePersonRepository responsiblePersonRepository;

    // Constructor for creating a new ResponsiblePersonDB object
    @Autowired
    public ResponsiblePersonDB(ResponsiblePersonRepository responsiblePersonRepository) {
        this.responsiblePersonRepository = responsiblePersonRepository;
    }

    // Returns the full name of a ResponsiblePerson object with the provided id
    public String getUserFullName(long id) {
        ResponsiblePerson user = getResponsiblePersonById(id).get();
        return user.getType() + user.getFirstName() + user.getLastName();
    }

    // Returns a List of all ResponsiblePerson objects in the database
    public List<ResponsiblePerson> getAllResponsiblePersons() {
        return (List<ResponsiblePerson>) responsiblePersonRepository.findAll();
    }

    // Returns an Optional of a ResponsiblePerson object with the provided id
    public Optional<ResponsiblePerson> getResponsiblePersonById(Long id) {
        return responsiblePersonRepository.findById(id);
    }

    // Returns an Optional of a ResponsiblePerson object with the provided email
    public Optional<ResponsiblePerson> getResponsiblePersonByEmail(String email) {
        if (email == null) {
            return Optional.empty();
        }

        Optional<ResponsiblePerson> optionalResponsiblePerson = responsiblePersonRepository.findByEmail(email);

        if (!optionalResponsiblePerson.isPresent()) {

            // In here, we are returning an empty optional
            return Optional.empty();
        }

        return optionalResponsiblePerson;
    }

    // Creates a new ResponsiblePerson object in the database and returns the created object
    public ResponsiblePerson createResponsiblePerson(ResponsiblePerson responsiblePerson) {
        return responsiblePersonRepository.save(responsiblePerson);
    }

    // Updates an existing ResponsiblePerson object in the database with the provided id and returns the updated object
    public ResponsiblePerson updateResponsiblePerson(Long id, ResponsiblePerson responsiblePerson) {
        responsiblePerson.setId(id);
        return responsiblePersonRepository.save(responsiblePerson);
    }

    // Deletes a ResponsiblePerson object from the database with the provided id
    public void deleteResponsiblePerson(Long id) {
        responsiblePersonRepository.deleteById(id);
    }
}

