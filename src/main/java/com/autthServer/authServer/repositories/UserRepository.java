package com.autthServer.authServer.repositories;

import com.autthServer.authServer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User>getUserByName(String name);

    Boolean existsEmail(String email);

    Optional<User> findUserOrEmail(String name,String email);
}
