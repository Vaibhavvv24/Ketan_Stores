package com.example.ketanStores.service;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import com.example.ketanStores.dto.Cotton_dto;
import com.example.ketanStores.entity.CottonEntity;
import com.example.ketanStores.enums.CottonEnum;
import com.example.ketanStores.enums.SilkEnum;

public interface Cotton_service {
    Cotton_dto getClothbyid(Long id);

    ArrayList<Cotton_dto> getall();

    ArrayList<Cotton_dto> getbytype(String type);

    ArrayList<Cotton_dto> getbysize(int size);

    String blobToBase64(Blob blob);

    Cotton_dto convert_entity_to_dto(CottonEntity cottonEntity);

    ArrayList<Cotton_dto> getByTypeAndSize(String type, int size);

    Cotton_dto createCotton(String name, int price, int quantity, CottonEnum cottonEnum, Blob blob, int size, String colour);

    ArrayList<Cotton_dto> getCottonByName(String name);

    ArrayList<Cotton_dto> getCottonByColour(String colour);

    ArrayList<Cotton_dto> getByTypeColour(String type, String colour);

    Cotton_dto updateCotton(Long id, int quantity);

    ArrayList<Cotton_dto> getByTypeColourSize(String type, String colour, int size);

    ArrayList<Cotton_dto> getByColourSize(int size, String colour);

    void deleteById(Long id);

    ArrayList<Cotton_dto> getByTypeSize(String type, int size);
}