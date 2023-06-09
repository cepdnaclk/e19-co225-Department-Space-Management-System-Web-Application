package com.example.SharedSpaces.repos;

import com.example.SharedSpaces.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
