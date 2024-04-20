package com.example.ketanStores.dto;

import java.sql.Blob;

import com.example.ketanStores.enums.CottonEnum;

public class Cotton_dto extends Kurta_dto{
    CottonEnum type;
    public Cotton_dto(CottonEnum type,String Name, int price, int size, int quantity, boolean available, String image) {
        super(Name,price, size, quantity, available, image);
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
    public boolean getavailable(){
        return available;
    }
    public String getimage(){
        return image;
    }
}
