package com.motherdiary.motherdiary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motherdiary.motherdiary.model.UserIdentity;



 public interface UserRepo extends JpaRepository<UserIdentity, Integer> {
 UserIdentity findByUsername(String username);

    
}
