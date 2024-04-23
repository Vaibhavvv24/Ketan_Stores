package com.example.ketanStores.entity;

import java.sql.Blob;

import com.example.ketanStores.enums.CottonEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CottonEntity {
    @Id
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private KurtaEntity kurtaEntity;

    private CottonEnum type;

    public String getName() {
        return kurtaEntity.getName();
    }

    public void setName(String name) {
        kurtaEntity.setName(name);
    }

    public CottonEntity(CottonEnum type) {
        this.type = type;
    }

    public Long getId() {
        return id;
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
    public void setQuantity(int quantity) {
        kurtaEntity.setQuantity(quantity);
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
    public void setPrice(int price) {
        kurtaEntity.setPrice(price);
    }
    public CottonEnum getType() {
        return type;
    }
    public Blob getImage() {
        return kurtaEntity.getImage();
    }
    public void setType(CottonEnum type) {
        this.type = type;
    }

    public String getColour() {
        return kurtaEntity.getColour();
    }

    public void setColour(String colour) {
        kurtaEntity.setColour(colour);
    }

    public void setSize(int size) {
        kurtaEntity.setSize(size);
    }

    public void setImage(Blob image) {
        kurtaEntity.setImage(image);
    }
}
