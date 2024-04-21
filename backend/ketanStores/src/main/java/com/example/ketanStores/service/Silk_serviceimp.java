package com.example.ketanStores.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import com.example.ketanStores.entity.KurtaEntity;
import com.example.ketanStores.enums.ChudidarEnum;
import com.example.ketanStores.repository.Kurta_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ketanStores.dto.Silk_dto;
import com.example.ketanStores.entity.SilkEntity;
import com.example.ketanStores.enums.SilkEnum;
import com.example.ketanStores.repository.Silk_Repo;

import io.jsonwebtoken.io.IOException;

@Service
public class Silk_serviceimp implements Silk_service{
    @Autowired
    private Silk_Repo silk_Repo;

    @Autowired
    private Kurta_repo kurtaRepo;

    @Override
    public Silk_dto getClothbyid(Long id) {
        Optional<SilkEntity> silk = silk_Repo.findById(id);
        if(silk.isPresent()){
            return convert_entity_to_dto(silk.get());
        }
        return null;
    }
    @Override
    public Silk_dto convert_entity_to_dto(SilkEntity silkEntity) {
        String Name = silkEntity.getName();
        SilkEnum Type = silkEntity.getType();
        int price = silkEntity.getPrice();
        int size = silkEntity.getSize();
        int quantity = silkEntity.getQuantity();
        boolean available = silkEntity.isAvailable();
        String image = blobToBase64(silkEntity.getImage());
        Silk_dto silk_dto = new Silk_dto(Type,Name,price, size, quantity, available, image);
        return silk_dto;
    }
    @Override
    public String blobToBase64(Blob blob){
        try (InputStream inputStream = blob.getBinaryStream()) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] imageBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public ArrayList<Silk_dto> getall() {
        ArrayList<Silk_dto> silk_dtos = new ArrayList<>();
        Iterable<SilkEntity> silks = silk_Repo.findAll();
        for(SilkEntity silk : silks){
            silk_dtos.add(convert_entity_to_dto(silk));
        }
        return silk_dtos;
    }
    @Override
    public ArrayList<Silk_dto> getbytype(String type) {
        ArrayList<Silk_dto> silk_dtos = new ArrayList<>();
        Iterable<SilkEntity> silks = silk_Repo.findAll();
        for(SilkEntity silk : silks){
            if(silk.getType().toString().equals(type)){
                silk_dtos.add(convert_entity_to_dto(silk));
            }
        }
        return silk_dtos;
    }
    @Override
    public ArrayList<Silk_dto> getbysize(int size) {
        ArrayList<Silk_dto> silk_dtos = new ArrayList<>();
        Iterable<SilkEntity> silks = silk_Repo.findAll();
        for(SilkEntity silk : silks){
            if(silk.getSize() == size){
                silk_dtos.add(convert_entity_to_dto(silk));
            }
        }
        return silk_dtos;
    }
    @Override
    public String add(SilkEntity silkEntity) {
        silk_Repo.save(silkEntity);
        return "Added";
    }

    @Override
    public ArrayList<Silk_dto> getByTypeAndSize(String type, int size) {
        ArrayList<Silk_dto> silk_dtos = new ArrayList<>();
        Iterable<SilkEntity> silkEntities = silk_Repo.findAll();
        for (SilkEntity silkEntity : silkEntities) {
            if (silkEntity.getType().toString().equals(type) && silkEntity.getSize() == size) {
                silk_dtos.add(convert_entity_to_dto(silkEntity));
            }
        }
        return silk_dtos;
    }

    @Override
    public Silk_dto createSilk(String name, int price, int quantity, SilkEnum silkEnum, Blob blob, int size) {
        KurtaEntity kurtaEntity = new KurtaEntity();
        SilkEntity silkEntity = new SilkEntity();
        kurtaEntity.setSize(size);
        kurtaEntity.setPrice(price);
        kurtaEntity.setAvailable(Boolean.TRUE);
        kurtaEntity.setQuantity(quantity);
        kurtaEntity.setImage(blob);
        silkEntity.setType(silkEnum);
        kurtaEntity.setName(name);
        Silk_dto silk_dto = new Silk_dto();
        silkEntity.setKurtaEntity(kurtaEntity);
        SilkEntity savedSilkEntity = silk_Repo.save(silkEntity);
        kurtaRepo.save(kurtaEntity);
        silk_dto.setId(savedSilkEntity.getId());
        return silk_dto;
    }

}
