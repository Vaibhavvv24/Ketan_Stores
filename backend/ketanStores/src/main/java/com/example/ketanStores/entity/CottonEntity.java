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
        return kurtaEntity.price;
    }
    public boolean isAvailable() {
        return kurtaEntity.available;
    }
    public int getSize() {
        return kurtaEntity.size;
    }
    public int getQuantity() {
        return kurtaEntity.quantity;
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
        kurtaEntity.price = price;
    }
    public void setAvailable(boolean available) {
        kurtaEntity.available = available;
    }
    public CottonEnum getType() {
        return type;
    }
    public Blob getImage() {
        return kurtaEntity.image;
    }
    public void setType(CottonEnum type) {
        this.type = type;
    }
    
}
