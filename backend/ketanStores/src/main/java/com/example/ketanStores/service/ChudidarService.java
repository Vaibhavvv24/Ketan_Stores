package com.example.ketanStores.service;

import com.example.ketanStores.dto.ChudidarDto;
import com.example.ketanStores.enums.ChudidarEnum;
import org.springframework.stereotype.Service;

import java.sql.Blob;

public interface ChudidarService{
    ChudidarDto createChuridar(String name, int price, int quantity, ChudidarEnum chudidarEnum, Blob blob,int size);
}
