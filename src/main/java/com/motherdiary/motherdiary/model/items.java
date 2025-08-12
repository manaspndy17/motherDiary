package com.motherdiary.motherdiary.model;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name="items")
public class items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserIdentity user; 

    private String item_name;
    private BigDecimal unit_price;
    private String item_image;


    public int getId(){return id;}
    public UserIdentity getUser(){return user;}
    public String getItemName(){return item_name;}
    public BigDecimal getUnitPrice(){return unit_price;}
    public String getItemImage(){return item_image;}


    public void setId(int id){this.id=id;}
    public void setUser(UserIdentity user){this.user=user;}
    public void setItemName(String item_name){this.item_name = item_name;}
    public void setUnitPrice(BigDecimal unit_price){this.unit_price=unit_price;}
    public void setItemImage(String item_image){this.item_image=item_image;}

    
}
