package com.example.ketanStores.service;

import com.example.ketanStores.dto.Others_dto;

public interface Other_service {
    Others_dto getOthersById(Long id);
    void postOthers(Others_dto othersDto);
    void putOthers(Others_dto othersDto);
    void deleteOthersById(Long id);
}
