package com.example.ketanStores.entity;

import java.sql.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cotton extends Kurta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public Cotton(String Type_name, int price, int size, int quantity, boolean available, Blob image) {
        super(Type_name, price, size, quantity, available, image);
    }
    public int getprice(){
        return super.price;
    }
    public int getsize(){
        return size;
    }
    public int getquantity(){
        return quantity;
    }
    public boolean getavailable(){
        return available;
    }
    public Blob getimage(){
        return image;
    }
}
