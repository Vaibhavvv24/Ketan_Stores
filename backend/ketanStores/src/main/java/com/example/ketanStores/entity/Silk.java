package com.example.ketanStores.entity;

import java.lang.annotation.Inherited;
import java.sql.Blob;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
public class Silk {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Kurta kurta;
}
