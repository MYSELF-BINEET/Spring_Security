package com.security.springSecurity.repository;


import com.security.springSecurity.entites.Role;
import com.security.springSecurity.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    User findByRole(Role email);
}
