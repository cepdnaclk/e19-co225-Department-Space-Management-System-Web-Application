package com.example.SharedSpaces.db;

import com.example.SharedSpaces.models.ResponsiblePerson;
import com.example.SharedSpaces.repos.ResponsiblePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponsiblePersonDB {


    private ResponsiblePersonRepository responsiblePersonRepository;

    @Autowired
    public ResponsiblePersonDB(ResponsiblePersonRepository responsiblePersonRepository){
        this.responsiblePersonRepository = responsiblePersonRepository;
    }

    public List<ResponsiblePerson> getAllResponsiblePersons() {
        return (List<ResponsiblePerson>) responsiblePersonRepository.findAll();
    }

    public Optional<ResponsiblePerson> getResponsiblePersonById(Long id) {
        return responsiblePersonRepository.findById(id);
    }
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


    public ResponsiblePerson createResponsiblePerson(ResponsiblePerson responsiblePerson) {
        return responsiblePersonRepository.save(responsiblePerson);
    }

    public ResponsiblePerson updateResponsiblePerson(Long id, ResponsiblePerson responsiblePerson) {
        responsiblePerson.setId(id);
        return responsiblePersonRepository.save(responsiblePerson);
    }

    public void deleteResponsiblePerson(Long id) {
        responsiblePersonRepository.deleteById(id);
    }
}

