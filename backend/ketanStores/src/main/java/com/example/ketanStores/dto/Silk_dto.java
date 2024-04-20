package com.example.ketanStores.dto;

import java.sql.Blob;

public class Silk_dto extends Kurta_dto{
    public Silk_dto(String Type_name, int price, int size, int quantity, boolean available, String image) {
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
    public String getimage(){
        return image;
    }
}
