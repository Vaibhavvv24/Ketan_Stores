package com.example.ketanStores.service;

import com.example.ketanStores.dto.Others_dto;
import com.example.ketanStores.enums.ChudidarEnum;
import com.example.ketanStores.enums.OthersEnum;

import java.sql.Blob;

public interface Other_service {
    Others_dto getOthersById(Long id);
    Others_dto postOthers(String name, int price, int quantity, OthersEnum othersEnum, Blob blob, int size, String colour);
    Others_dto putOthers(String name, int price, int quantity, OthersEnum othersEnum, Blob blob,int size, String colour);
    void deleteOthersById(Long id);
}
