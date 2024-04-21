package com.example.ketanStores.repository;

import com.example.ketanStores.enums.CottonEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ketanStores.entity.CottonEntity;

import java.util.List;

public interface Cotton_repo extends JpaRepository<CottonEntity,Long>{
    List<CottonEntity> findAllByType(CottonEnum cottonEnum);
}
