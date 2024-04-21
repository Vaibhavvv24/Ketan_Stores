package com.example.ketanStores.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;

import com.example.ketanStores.dto.Silk_dto;
import com.example.ketanStores.entity.KurtaEntity;
import com.example.ketanStores.entity.SilkEntity;
import com.example.ketanStores.repository.Kurta_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ketanStores.dto.Cotton_dto;
import com.example.ketanStores.entity.CottonEntity;
import com.example.ketanStores.enums.CottonEnum;
import com.example.ketanStores.repository.Cotton_repo;

@Service
public class Cotton_serviceimp implements Cotton_service{

    @Autowired
    private Cotton_repo cotton_Repo;

    @Autowired
    private Kurta_repo kurtaRepo;

    @Override
    public Cotton_dto getClothbyid(Long id) {
        Optional<CottonEntity> silk = cotton_Repo.findById(id);
        if(silk.isPresent()){
            return convert_entity_to_dto(silk.get());
        }
        return null;
    }
    @Override
    public Cotton_dto convert_entity_to_dto(CottonEntity cottonEntity) {
        String Name = cottonEntity.getName();
        CottonEnum Type = cottonEntity.getType();
        int price = cottonEntity.getPrice();
        int size = cottonEntity.getSize();
        int quantity = cottonEntity.getQuantity();
        boolean available = cottonEntity.isAvailable();
        String image = blobToBase64(cottonEntity.getImage());
        Cotton_dto cotton_dto = new Cotton_dto(Type,Name,price, size, quantity, available, image);
        return cotton_dto;
    }

    @Override
    public ArrayList<Cotton_dto> getByTypeAndSize(String type, int size) {
        ArrayList<Cotton_dto> cotton_dtos = new ArrayList<>();
        Iterable<CottonEntity> cottonDtos = cotton_Repo.findAll();
        for (CottonEntity cottonEntity : cottonDtos) {
            if (cottonEntity.getType().toString().equals(type) && cottonEntity.getSize() == size) {
                cotton_dtos.add(convert_entity_to_dto(cottonEntity));
            }
        }
        return cotton_dtos;
    }

    @Override
    public Cotton_dto createCotton(String name, int price, int quantity, CottonEnum cottonEnum, Blob blob, int size) {
        KurtaEntity kurtaEntity = new KurtaEntity();
        CottonEntity cottonEntity = new CottonEntity();
        kurtaEntity.setSize(size);
        kurtaEntity.setPrice(price);
        kurtaEntity.setAvailable(Boolean.TRUE);
        kurtaEntity.setQuantity(quantity);
        kurtaEntity.setImage(blob);
        cottonEntity.setType(cottonEnum);
        kurtaEntity.setName(name);
        Cotton_dto cotton_dto = new Cotton_dto();
        cottonEntity.setKurtaEntity(kurtaEntity);
        CottonEntity savedCottonEntity = cotton_Repo.save(cottonEntity);
        kurtaRepo.save(kurtaEntity);
        cotton_dto.setId(savedCottonEntity.getId());
        return cotton_dto;
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
    public ArrayList<Cotton_dto> getall() {
        ArrayList<Cotton_dto> cotton_dtos = new ArrayList<>();
        Iterable<CottonEntity> cottons = cotton_Repo.findAll();
        for(CottonEntity cotton : cottons){
            cotton_dtos.add(convert_entity_to_dto(cotton));
        }
        return cotton_dtos;
    }

    @Override
    public ArrayList<Cotton_dto> getbytype(String type) {
        //code
        ArrayList<Cotton_dto> cotton_dtos = new ArrayList<>();
        Iterable<CottonEntity> cottons = cotton_Repo.findAll();
        for(CottonEntity cotton : cottons){
            if(cotton.getType().toString().equals(type)){
                cotton_dtos.add(convert_entity_to_dto(cotton));
            }
        }
        return cotton_dtos;
    }

    @Override
    public ArrayList<Cotton_dto> getbysize(int size) {
        //code
        ArrayList<Cotton_dto> cotton_dtos = new ArrayList<>();
        Iterable<CottonEntity> cottons = cotton_Repo.findAll();
        for(CottonEntity cotton : cottons){
            if(cotton.getSize() == size){
                cotton_dtos.add(convert_entity_to_dto(cotton));
            }
        }
        return cotton_dtos;
    } 
}
