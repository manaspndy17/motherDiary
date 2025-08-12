package com.motherdiary.motherdiary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")

public class UserIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    
    private String username;
    private String password;
    private String tell;
    private String email;
    private String profileImage;
    private String coverImage;


public String getProfileImage(){
    return profileImage;
}    

public String getCoverImage(){
    return coverImage;
}

public int getId() { return id; }
public String getUsername() { return username; }
public String getPassword() { return password; }
public String getTell() { return tell; }
public String getEmail() { return email; }

public void setId(int id) { this.id = id; }
public void setUsername(String username) { this.username = username; }
public void setPassword(String password) { this.password = password; }
public void setTell(String tell) { this.tell = tell; }
public void setEmail(String email) { this.email = email; }

public void setProfileImage(String profile_image){this.profileImage = profile_image;}
public void setCoverImage(String cover_image){this.coverImage = cover_image;}



    

    
}
