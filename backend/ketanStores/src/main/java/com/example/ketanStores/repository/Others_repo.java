package com.example.ketanStores.repository;

import com.example.ketanStores.entity.ChudidarEntity;
import com.example.ketanStores.enums.OthersEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ketanStores.entity.OthersEntity;

import java.util.Collection;
import java.util.List;

@Repository
public interface Others_repo extends JpaRepository<OthersEntity,Long>{
    List<OthersEntity> findAllByType(OthersEnum othersEnum);

    List<OthersEntity> findAllByTypeAndSize(OthersEnum othersEnum, int size);

    List<OthersEntity> findAllByName(String name);

    List<OthersEntity> findAllByTypeAndSizeAndColour(OthersEnum othersEnum, int size, String colour);

    List<OthersEntity> findAllByTypeAndColour(OthersEnum othersEnum, String colour);
}
