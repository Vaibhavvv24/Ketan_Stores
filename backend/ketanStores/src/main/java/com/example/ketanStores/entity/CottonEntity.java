package com.example.ketanStores.entity;

import com.example.ketanStores.enums.CottonEnum;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
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
