package com.example.ketanStores.dto;

import java.sql.Blob;

import com.example.ketanStores.enums.CottonEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cotton_dto extends Kurta_dto{
    CottonEnum type;
    public Cotton_dto(CottonEnum type,String Name, int price, int size, int quantity, String image) {
        super(Name,price, size, quantity, image);
        this.type = type;
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
    public String getimage(){
        return image;
    }
}
