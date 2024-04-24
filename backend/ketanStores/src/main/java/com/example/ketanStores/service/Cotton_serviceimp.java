package com.example.ketanStores.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.ketanStores.dto.Silk_dto;
import com.example.ketanStores.entity.ChudidarEntity;
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
        String image = blobToBase64(cottonEntity.getImage());
        String colour = cottonEntity.getColour();
        Cotton_dto cotton_dto = new Cotton_dto(Type,Name,price, size, quantity, image, colour);
        cotton_dto.setId(cottonEntity.getId());
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
    public Cotton_dto createCotton(String name, int price, int quantity, CottonEnum cottonEnum, Blob blob, int size, String colour) {
        KurtaEntity kurtaEntity = new KurtaEntity();
        CottonEntity cottonEntity = new CottonEntity();
        kurtaEntity.setSize(size);
        kurtaEntity.setPrice(price);
        kurtaEntity.setQuantity(quantity);
        kurtaEntity.setImage(blob);
        kurtaEntity.setColour(colour);
        cottonEntity.setType(cottonEnum);
        kurtaEntity.setName(name);

        Cotton_dto cotton_dto = new Cotton_dto();
        cottonEntity.setKurtaEntity(kurtaEntity);
        kurtaRepo.save(kurtaEntity);
        CottonEntity savedCottonEntity = cotton_Repo.save(cottonEntity);

        cotton_dto.setId(savedCottonEntity.getId());
        return cotton_dto;
    }

    @Override
    public ArrayList<Cotton_dto> getCottonByName(String name) {
        ArrayList<Cotton_dto> cotton_dtos = new ArrayList<>();
        Iterable<CottonEntity> cottons = cotton_Repo.findAll();
        for(CottonEntity cotton : cottons){
            if(cotton.getName().equals(name)){
                cotton_dtos.add(convert_entity_to_dto(cotton));
            }
        }
        return cotton_dtos;
    }

    @Override
    public ArrayList<Cotton_dto> getCottonByColour(String colour) {
        ArrayList<Cotton_dto> cotton_dtos = new ArrayList<>();
        Iterable<CottonEntity> cottonEntities = cotton_Repo.findAll();
        for (CottonEntity cottonEntity : cottonEntities) {
            if (cottonEntity.getColour().equals(colour)) {
                cotton_dtos.add(convert_entity_to_dto(cottonEntity));
            }
        }
        return cotton_dtos;
    }

    @Override
    public ArrayList<Cotton_dto> getByTypeColour(String type, String colour) {
        ArrayList<Cotton_dto> cotton_dtos = new ArrayList<>();
        Iterable<CottonEntity> cottonEntities = cotton_Repo.findAll();
        for (CottonEntity cottonEntity : cottonEntities) {
            if (cottonEntity.getColour().equals(colour) && cottonEntity.getType().toString().equals(type)) {
                cotton_dtos.add(convert_entity_to_dto(cottonEntity));
            }
        }
        return cotton_dtos;
    }

    @Override
    public Cotton_dto updateCotton(Long id, int price, int quantity, String colour) {
        CottonEntity cottonEntity = cotton_Repo.findById(id).get();
        cottonEntity.setColour(colour);
        cottonEntity.setPrice(price);
        cottonEntity.setQuantity(quantity);
        return this.convert_entity_to_dto(cotton_Repo.save(cottonEntity));
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
        System.out.println(cotton_dtos);
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
        ArrayList<Cotton_dto> cotton_dtos = new ArrayList<>();
        Iterable<CottonEntity> cottons = cotton_Repo.findAll();
        for(CottonEntity cotton : cottons){
            if(cotton.getSize() == size){
                cotton_dtos.add(convert_entity_to_dto(cotton));
            }
        }
        return cotton_dtos;
    }
    @Override
    public ArrayList<Cotton_dto> getByTypeColourSize(String type, String colour, int size) {
        ArrayList<Cotton_dto> cotton_dtos = new ArrayList<>();
        Iterable<CottonEntity> cottonEntities = cotton_Repo.findAll();
        for (CottonEntity cottonEntity : cottonEntities) {
            if (cottonEntity.getType().toString().equals(type) && cottonEntity.getColour().equals(colour) && cottonEntity.getSize() == size) {
                cotton_dtos.add(convert_entity_to_dto(cottonEntity));
            }
        }
        return cotton_dtos;
    }


}
