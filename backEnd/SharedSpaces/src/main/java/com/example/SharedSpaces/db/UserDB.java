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

    public Optional<User> getUserByEmail(String email){
        for (User user: this.getAllUsers()){
            if (user.getEmail().equals(email)) {
                return Optional
                        .ofNullable(user);
            }
        }
        return  Optional
                .ofNullable(null);
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

