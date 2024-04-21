package com.example.ketanStores.entity;

import java.sql.Blob;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class KurtaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String Name;
    int price;
    int size;
    int quantity;
    boolean available;
    Blob image;

    public KurtaEntity(String Name,int price, int size, int quantity, boolean available, Blob image) {
        this.Name = Name;
        this.price = price;
        this.size = size;
        this.quantity = quantity;
        this.available = available;
        this.image = image;
    }
    public String getName() {
        return Name;
    }
    public Long getId() {
        return id;
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

    public boolean getAvailable() {
        return available;
    }

    public Blob getImage() {
        return image;
    }

    public void setId(Long id) {
        this.id = id;
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
    public void setName(String Name) {
        this.Name = Name;
    }
}
