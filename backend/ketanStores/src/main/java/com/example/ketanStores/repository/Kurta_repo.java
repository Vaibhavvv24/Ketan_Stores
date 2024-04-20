package com.example.ketanStores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ketanStores.entity.Kurta;

@Repository
public interface Kurta_repo extends JpaRepository<Kurta,Long>{
    
}
