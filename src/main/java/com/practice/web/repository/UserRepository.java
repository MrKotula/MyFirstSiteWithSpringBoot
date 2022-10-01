package com.practice.web.repository;
import com.practice.web.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);
    User findByPassword(String password);
    User findByRoles(String roles);
}
