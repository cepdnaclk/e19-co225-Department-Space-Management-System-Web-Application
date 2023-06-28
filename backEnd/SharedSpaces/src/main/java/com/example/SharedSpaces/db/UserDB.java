package com.example.SharedSpaces.db;

import com.example.SharedSpaces.models.User;
import com.example.SharedSpaces.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// The @Service annotation indicates that this class is a Spring service
@Service
public class UserDB {

    // The UserRepository used by this class
    private UserRepository userRepository;

    // Constructor for creating a new UserDB object
    @Autowired
    public UserDB(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Returns a List of all User objects in the database
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    // Returns an Optional of a User object with the provided id
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Returns the full name of a User object with the provided id
    public String getUserFullName(long id) {
        User user = getUserById(id).get();
        return user.getFirstName() + user.getLastName();
    }

    // Returns an Optional of a User object with the provided email
    public Optional<User> getUserByEmail(String email) {
        if (email == null) {
            return Optional.empty();
        }

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (!optionalUser.isPresent()) {

            // In here, we are returning an empty optional
            return Optional.empty();
        }

        return optionalUser;
    }

    // Creates a new User object in the database and returns the created object
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Updates an existing User object in the database with the provided id and returns the updated object
    public User updateUser(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    // Deletes a User object from the database with the provided id
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}

