package com.example.ketanStores.repository;

import com.example.ketanStores.enums.SilkEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ketanStores.entity.SilkEntity;

import java.util.List;

@Repository
public interface Silk_Repo extends JpaRepository<SilkEntity,Long>{
    List<SilkEntity> findAllByType(SilkEnum silkEnum);
}
