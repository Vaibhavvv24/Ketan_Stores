package com.example.ketanStores.entity;

import java.sql.Blob;

import com.example.ketanStores.enums.ChudidarEnum;
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
public class ChudidarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private ChudidarEnum Type_name;
    private int size;
    private boolean available;
    private int quantity;
    private int price;
    private Blob image;
}
