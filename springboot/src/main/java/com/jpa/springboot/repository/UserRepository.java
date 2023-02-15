package com.jpa.springboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jpa.springboot.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {    
}
