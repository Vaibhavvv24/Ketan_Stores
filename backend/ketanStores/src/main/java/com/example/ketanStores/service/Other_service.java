package com.example.ketanStores.service;

import com.example.ketanStores.dto.ChudidarDto;
import com.example.ketanStores.dto.Others_dto;
import com.example.ketanStores.enums.ChudidarEnum;
import com.example.ketanStores.enums.OthersEnum;

import java.sql.Blob;
import java.util.List;

public interface Other_service {
    Others_dto getOtherById(Long id);

    List<Others_dto> getOthers();

    List<Others_dto> getOthersByType(String type);

    Others_dto createOther(String name, int price, int quantity, OthersEnum othersEnum, Blob blob, int size, String colour);

    Others_dto updateOther(Long id,int quantity);

    List<Others_dto> getOtherByTypeandSize(String type, int size);

    void deleteById(Long id);

    List<Others_dto> getOthersByName(String name);

    List<Others_dto> getOtherByTypeandSizeandColour(String type, int size, String colour);

    List<Others_dto> getOtherByTypeandColour(String type, String colour);
}
