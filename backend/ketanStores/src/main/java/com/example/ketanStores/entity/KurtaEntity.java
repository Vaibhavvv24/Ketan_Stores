package com.example.ketanStores.entity;

import java.sql.Blob;

import jakarta.persistence.*;

@Entity
public class KurtaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String Type_name;
    int price;
    int size;
    int quantity;
    boolean available;
    Blob image;

    public KurtaEntity(String type_name, int price, int size, int quantity, boolean available, Blob image) {
        Type_name = type_name;
        this.price = price;
        this.size = size;
        this.quantity = quantity;
        this.available = available;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getType_name() {
        return Type_name;
    }

    public int getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isAvailable() {
        return available;
    }

    public Blob getImage() {
        return image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType_name(String type_name) {
        Type_name = type_name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
