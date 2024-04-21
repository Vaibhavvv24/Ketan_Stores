package com.example.ketanStores.repository;

import com.example.ketanStores.enums.ChudidarEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ketanStores.entity.ChudidarEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Repository
public interface Chudidar_repo extends JpaRepository<ChudidarEntity,Long>{

    List<ChudidarEntity> findAllByType(ChudidarEnum chudidarEnum);

    List<ChudidarEntity> findAllByTypeAndSize(String type, int size);
}
