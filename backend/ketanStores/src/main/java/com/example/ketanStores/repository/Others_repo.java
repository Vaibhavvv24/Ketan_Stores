package com.example.ketanStores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ketanStores.entity.Others;

@Repository
public interface Others_repo extends JpaRepository<Others,Long>{

}
