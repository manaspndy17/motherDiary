package com.motherdiary.motherdiary.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motherdiary.motherdiary.model.UserIdentity;
import com.motherdiary.motherdiary.model.item_entry;

public interface  ItemEntryRepo extends  JpaRepository<item_entry,Integer> {

   List<item_entry> findByUser(UserIdentity user);

    List<item_entry> findByUserAndEntryDateBetween(UserIdentity userId, LocalDate start, LocalDate end);

    List<item_entry> findByUserIdAndItemId(int userId, int itemId);
    
}
