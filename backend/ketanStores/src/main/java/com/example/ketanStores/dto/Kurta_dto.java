package com.example.ketanStores.dto;

import java.sql.Blob;

public class Kurta_dto {
    Long id;
    String Type_name;
    int price;
    int size;
    int quantity;
    boolean available;
    String image;
    public Kurta_dto(String Type_name, int price, int size, int quantity, boolean available, String image) {
        this.Type_name = Type_name;
        this.price = price;
        this.size = size;
        this.quantity = quantity;
        this.available = available;
        this.image = image;
    }
}
