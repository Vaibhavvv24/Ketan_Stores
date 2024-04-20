package com.example.ketanStores.service;

import java.sql.Blob;
import java.util.ArrayList;

import com.example.ketanStores.dto.Cotton_dto;
import com.example.ketanStores.entity.CottonEntity;

public interface Cotton_service {

    Cotton_dto getClothbyid(Long id);

    ArrayList<Cotton_dto> getall();

    ArrayList<Cotton_dto> getbytype(String type);

    ArrayList<Cotton_dto> getbysize(int size);
    String blobToBase64(Blob blob);
    Cotton_dto convert_entity_to_dto(CottonEntity cottonEntity);
}