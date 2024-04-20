package com.example.ketanStores.dto;

import com.example.ketanStores.enums.others_enum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Others_dto {
    private Long id;
    others_enum Type_name;
    int price;
    int size;
    int quantity;
    boolean available;
    Blob image;
}
