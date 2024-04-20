package com.example.ketanStores.dto;

import com.example.ketanStores.enums.ChudidarEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChudidarDto {
    ChudidarEnum Type_name;
    int price;
    int size;
    int quantity;
    boolean available;
    String image;
}
