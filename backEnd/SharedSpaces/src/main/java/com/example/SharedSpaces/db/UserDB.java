package com.example.SharedSpaces.db;

import com.example.SharedSpaces.models.User;
import com.example.SharedSpaces.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDB {


    private UserRepository userRepository;

    @Autowired
    public UserDB(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public String getUserFullName(long id){
        User user = getUserById(id).get();
        return user.getFirstName() + user.getLastName();
    }

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

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}

