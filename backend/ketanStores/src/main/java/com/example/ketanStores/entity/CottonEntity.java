package com.example.ketanStores.entity;

import com.example.ketanStores.enums.CottonEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
public class CottonEntity {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private KurtaEntity kurtaEntity;

    private CottonEnum type;

    public CottonEntity(CottonEnum type) {
        this.type = type;
    }
}
