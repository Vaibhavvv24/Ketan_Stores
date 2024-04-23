package com.example.ketanStores.dto;

import java.sql.Blob;

import com.example.ketanStores.enums.SilkEnum;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Kurta_dto {
    Long id;
    String Name;
    int price;
    int size;
    int quantity;
    String image;
    String colour;
    public Kurta_dto(String Name,int price, int size, int quantity, String image, String colour) {
        this.price = price;
        this.size = size;
        this.quantity = quantity;
        this.image = image;
        this.Name = Name;
        this.colour = colour;
    }
}
