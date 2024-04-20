package com.example.ketanStores.entity;

import java.sql.Blob;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Kurta {
    String Type_name;
    int price;
    int size;
    int quantity;
    boolean available;
    Blob image;
}
