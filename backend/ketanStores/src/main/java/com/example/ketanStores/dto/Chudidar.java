package com.example.ketanStores.dto;

import java.sql.Blob;

import jakarta.persistence.Entity;

@Entity
public class Chudidar extends Kurta_dto{
    public Chudidar(String Type_name, int price, int size, int quantity, boolean available, Blob image) {
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
