package com.motherdiary.motherdiary.model;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_entries")
public class item_entry {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserIdentity user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private items item;

    private int units;

    private LocalDate entryDate;


    public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public UserIdentity getUser() {
    return user;
}

public void setUser(UserIdentity user) {
    this.user = user;
}

public items getItem() {
    return item;
}

public void setItem(items item) {
    this.item = item;
}

public int getUnits() {
    return units;
}

public void setUnits(int units) {
    this.units = units;
}

public LocalDate getEntryDate() {
    return entryDate;
}

public void setEntryDate(LocalDate entryDate) {
    this.entryDate = entryDate;
}

    
}
