package com.example.ketanStores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ketanStores.entity.Chudidar;

@Repository
public interface Chudidar_repo extends JpaRepository<Chudidar,Long>{
    
}
