package com.example.ketanStores.dto;

import java.sql.Blob;

import com.example.ketanStores.enums.SilkEnum;

public class Kurta_dto {
    String Name;
    int price;
    int size;
    int quantity;
    boolean available;
    String image;
    public Kurta_dto(String Name,int price, int size, int quantity, boolean available, String image) {
        this.price = price;
        this.size = size;
        this.quantity = quantity;
        this.available = available;
        this.image = image;
    }
}
