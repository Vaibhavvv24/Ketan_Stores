package com.example.ketanStores.entity;

import java.sql.Blob;

import com.example.ketanStores.enums.SilkEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SilkEntity {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    private KurtaEntity kurtaEntity;

    private SilkEnum type;

    public Long getId() {
        return id;
    }
    public String getName() {
        return kurtaEntity.getName();
    }
    public void setName(String name) {
        kurtaEntity.setName(name);
    }
    public void setId(Long id) {
        this.id = id;
    }
    public KurtaEntity getKurtaEntity() {
        return kurtaEntity;
    }
    public void setKurtaEntity(KurtaEntity kurtaEntity) {
        this.kurtaEntity = kurtaEntity;
    }
    public Blob getImage() {
        return kurtaEntity.getImage();
    }
    public SilkEnum getType() {
        return type;
    }
    public int getPrice() {
        return kurtaEntity.getPrice();
    }
    public int getSize() {
        return kurtaEntity.getSize();
    }
    public int getQuantity() {
        return kurtaEntity.getQuantity();
    }
    public void setImage(Blob image) {
        kurtaEntity.setImage(image);
    }
    public void setPrice(int price) {
        kurtaEntity.setPrice(price);
    }
    public void setSize(int size) {
        kurtaEntity.setSize(size);
    }
    public void setType(SilkEnum type) {
        this.type = type;
    }

    public SilkEntity(SilkEnum type) {
        this.type = type;
    }

    public String getColour() {
        return kurtaEntity.getColour();
    }

    public void setColour(String colour) {
        kurtaEntity.setColour(colour);
    }

    public void setQuantity(int quantity) {
        kurtaEntity.setQuantity(quantity);
    }
}
