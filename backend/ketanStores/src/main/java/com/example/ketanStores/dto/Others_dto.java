package com.example.ketanStores.dto;

import com.example.ketanStores.enums.OthersEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Others_dto {
    private Long id;
    OthersEnum TypeName;
    String Name;
    int price;
    int size;
    int quantity;
    boolean available;
    String image;
    String colour;
}
