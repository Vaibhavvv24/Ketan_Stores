package com.example.ketanStores.service;

import com.example.ketanStores.dto.ChudidarDto;
import com.example.ketanStores.entity.ChudidarEntity;
import com.example.ketanStores.enums.ChudidarEnum;
import com.example.ketanStores.repository.Chudidar_repo;
import org.springframework.stereotype.Service;

import java.sql.Blob;

@Service
public class ChudidarServiceimp implements ChudidarService{

    private final Chudidar_repo chudidarRepo;

    public ChudidarServiceimp(Chudidar_repo chudidarRepo) {
        this.chudidarRepo = chudidarRepo;
    }

    @Override
    public ChudidarDto createChuridar(String name, int price, int quantity, ChudidarEnum chudidarEnum, Blob blob,int size) {
        ChudidarEntity chudidarEntity=new ChudidarEntity();
        chudidarEntity.setSize(size);
        chudidarEntity.setPrice(price);
        chudidarEntity.setAvailable(Boolean.TRUE);
        chudidarEntity.setQuantity(quantity);
        chudidarEntity.setImage(blob);
        chudidarEntity.setType_name(chudidarEnum);
        chudidarEntity.setName(name);
        ChudidarDto chudidarDto=new ChudidarDto();
        ChudidarEntity savedChuridar=chudidarRepo.save(chudidarEntity);
        chudidarDto.se
    }
}
