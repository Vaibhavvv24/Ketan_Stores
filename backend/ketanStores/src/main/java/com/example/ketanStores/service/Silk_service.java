package com.example.ketanStores.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.ketanStores.dto.Silk_dto;
import com.example.ketanStores.entity.SilkEntity;

import com.example.ketanStores.enums.ChudidarEnum;
import com.example.ketanStores.enums.SilkEnum;
import io.jsonwebtoken.io.IOException;

public interface Silk_service{
    Silk_dto getClothbyid(Long id);

    Silk_dto convert_entity_to_dto(SilkEntity silkEntity);

    String blobToBase64(Blob blob);

    ArrayList<Silk_dto> getall();

    ArrayList<Silk_dto> getbytype(String type);

    ArrayList<Silk_dto> getbysize(int size);

    String add(SilkEntity silkEntity);

    ArrayList<Silk_dto> getByTypeAndSize(String type, int size);

    Silk_dto createSilk(String name, int price, int quantity, SilkEnum silkEnum, Blob blob, int size);

    ArrayList<Silk_dto> getSilkByName(String name);
}
