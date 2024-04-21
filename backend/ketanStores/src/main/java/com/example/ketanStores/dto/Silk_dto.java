package com.example.ketanStores.dto;

import java.sql.Blob;

import com.example.ketanStores.enums.SilkEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Silk_dto extends Kurta_dto{
    SilkEnum Type;
    public Silk_dto(SilkEnum Type,String Name, int price, int size, int quantity, boolean available, String image) {
        super(Name,price, size, quantity, available, image);
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
    public boolean getavailable(){
        return available;
    }
    public String getimage(){
        return image;
    }
}
