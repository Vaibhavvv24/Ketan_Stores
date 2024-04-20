package com.example.ketanStores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ketanStores.entity.KurtaEntity;

@Repository
public interface Kurta_repo extends JpaRepository<KurtaEntity,Long>{
    
}
