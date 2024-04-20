package com.example.ketanStores.entity;
import java.sql.Blob;

import com.example.ketanStores.enums.others_enum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Others{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    others_enum Type_name;
    int price;
    int size;
    int quantity;
    boolean available;
    Blob image;
}
