package com.example.ketanStores.dto;

import java.sql.Blob;

import com.example.ketanStores.enums.SilkEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Silk_dto extends Kurta_dto{
    SilkEnum Type;
    public Silk_dto(SilkEnum Type,String Name, int price, int size, int quantity, String image, String colour) {
        super(Name,price, size, quantity, image, colour);
        this.Type = Type;
    }
    public Silk_dto() {
    }
    public int getprice(){
        return price;
    }
    public int getsize(){
        return size;
    }
    public int getquantity(){
        return quantity;
    }
    public String getimage(){
        return image;
    }

    @Override
    public String getColour() {
        return colour;
    }
}
