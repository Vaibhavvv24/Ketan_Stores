package com.example.ketanStores.entity;

import java.sql.Blob;

import com.example.ketanStores.enums.KurtaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class KurtaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String Name;
    private int price;
    private int size;
    private int quantity;
    private String colour;
    private KurtaEnum kurtaEnum;
    @Column(columnDefinition = "longblob")
    private Blob image;

    @OneToOne(mappedBy = "kurtaEntity", cascade = CascadeType.ALL)
    private CottonEntity cottonEntity;

    @OneToOne(mappedBy = "kurtaEntity", cascade = CascadeType.ALL)
    private SilkEntity silkEntity;

    public KurtaEntity(String Name,int price, int size, int quantity, Blob image, String colour, KurtaEnum kurtaEnum) {
        this.Name = Name;
        this.price = price;
        this.size = size;
        this.quantity = quantity;
        this.image = image;
        this.colour = colour;
        this.kurtaEnum = kurtaEnum;
    }
    public String getName() {
        return Name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }

    public Blob getImage() {
        return image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
    public void setName(String Name) {
        this.Name = Name;
    }

    public KurtaEnum getKurtaEnum() {
        return kurtaEnum;
    }

    public void setKurtaEnum(KurtaEnum kurtaEnum) {
        this.kurtaEnum = kurtaEnum;
    }
}
