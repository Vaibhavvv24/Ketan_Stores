package com.example.ketanStores.entity;

import java.sql.Blob;

import com.example.ketanStores.enums.SilkEnum;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
public class SilkEntity {
    @Id
    private Long id;
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
    public boolean isAvailable() {
        return kurtaEntity.getAvailable();
    }
    public void setImage(Blob image) {
        kurtaEntity.image = image;
    }
    public void setPrice(int price) {
        kurtaEntity.price = price;
    }
    public void setSize(int size) {
        kurtaEntity.size = size;
    }
    public void setType(SilkEnum type) {
        this.type = type;
    }
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private KurtaEntity kurtaEntity;

    private SilkEnum type;

    public SilkEntity(SilkEnum type) {
        this.type = type;
    }
}
