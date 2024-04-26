package com.example.ketanStores.repository;

import com.example.ketanStores.entity.ChudidarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ketanStores.entity.KurtaEntity;

import java.util.List;

@Repository
public interface Kurta_repo extends JpaRepository<KurtaEntity,Long>{
    List<KurtaEntity> findAllByNameContaining(String name);
}
