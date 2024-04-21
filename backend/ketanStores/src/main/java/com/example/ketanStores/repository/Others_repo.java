package com.example.ketanStores.repository;

import com.example.ketanStores.enums.OthersEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ketanStores.entity.OthersEntity;

import java.util.List;

@Repository
public interface Others_repo extends JpaRepository<OthersEntity,Long>{
    List<OthersEntity> findAllByType(OthersEnum othersEnum);
}
