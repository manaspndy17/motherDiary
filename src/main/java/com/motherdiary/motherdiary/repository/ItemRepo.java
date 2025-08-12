package com.motherdiary.motherdiary.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motherdiary.motherdiary.model.UserIdentity;
import com.motherdiary.motherdiary.model.items;

public interface ItemRepo extends JpaRepository<items, Integer > {

    List<items> findByUser(UserIdentity user); 

    
}
