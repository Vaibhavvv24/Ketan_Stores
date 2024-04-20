package com.example.ketanStores.entity;

import com.example.ketanStores.enums.SilkEnum;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
public class SilkEntity {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private KurtaEntity kurtaEntity;

    private SilkEnum type;

    public SilkEntity(SilkEnum type) {
        this.type = type;
    }
}
