package com.example.ketanStores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ketanStores.entity.SilkEntity;

@Repository
public interface Silk_Repo extends JpaRepository<SilkEntity,Long>{
    
}
