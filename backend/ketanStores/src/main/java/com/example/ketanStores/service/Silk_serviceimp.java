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
import java.util.stream.Collectors;

import com.example.ketanStores.entity.KurtaEntity;
import com.example.ketanStores.entity.OthersEntity;
import com.example.ketanStores.enums.ChudidarEnum;
import com.example.ketanStores.enums.KurtaEnum;
import com.example.ketanStores.repository.Kurta_repo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ketanStores.dto.Silk_dto;
import com.example.ketanStores.entity.SilkEntity;
import com.example.ketanStores.enums.SilkEnum;
import com.example.ketanStores.repository.Silk_Repo;

import io.jsonwebtoken.io.IOException;

@Slf4j
@Service
public class Silk_serviceimp implements Silk_service{
    @Autowired
    private Silk_Repo silk_Repo;

    @Autowired
    private Kurta_repo kurtaRepo;

    @Override
    public Silk_dto getClothbyid(Long id) {
        log.info("Silk_serviceimp: Received a request to get silk with id = {}", id);
        Optional<SilkEntity> silk = silk_Repo.findById(id);
        if(silk.isPresent()){
            log.info("Silk_serviceimp: Returning silk = {}", silk);
            return convert_entity_to_dto(silk.get());
        }
        log.error("Silk_serviceimp: Cannot find silk with id = {}", id);
        return null;
    }
    @Override
    public Silk_dto convert_entity_to_dto(SilkEntity silkEntity) {
        String Name = silkEntity.getName();
        SilkEnum Type = silkEntity.getType();
        int price = silkEntity.getPrice();
        int size = silkEntity.getSize();
        int quantity = silkEntity.getQuantity();
        String image = blobToBase64(silkEntity.getImage());
        String colour = silkEntity.getColour();
        Silk_dto silk_dto = new Silk_dto(Type,Name,price, size, quantity, image, colour);
        silk_dto.setId(silkEntity.getId());
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
        log.info("Silk_serviceimp: Received a request to get all silks.");
        ArrayList<Silk_dto> silk_dtos = new ArrayList<>();
        Iterable<SilkEntity> silks = silk_Repo.findAll();
        for(SilkEntity silk : silks){
            silk_dtos.add(convert_entity_to_dto(silk));
        }
        log.info("Silk_serviceimp: Returning all silks.");
        return silk_dtos;
    }

    @Override
    public ArrayList<Silk_dto> getbytype(String type) {
        log.info("Silk_serviceimp: Received a request to get silks wrt type filter, type = {}", type);
        ArrayList<Silk_dto> silk_dtos = new ArrayList<>();
        Iterable<SilkEntity> silks = silk_Repo.findAll();
        for(SilkEntity silk : silks){
            if(silk.getType().toString().equals(type)){
                silk_dtos.add(convert_entity_to_dto(silk));
            }
        }
        log.info("Silk_serviceimp: Returning the filtered silks.");
        return silk_dtos;
    }

    @Override
    public ArrayList<Silk_dto> getbysize(int size) {
        log.info("Silk_serviceimp: Received a request to get silks wrt size filter, size = {}", size);
        ArrayList<Silk_dto> silk_dtos = new ArrayList<>();
        Iterable<SilkEntity> silks = silk_Repo.findAll();
        for(SilkEntity silk : silks){
            if(silk.getSize() == size){
                silk_dtos.add(convert_entity_to_dto(silk));
            }
        }
        log.info("Silk_serviceimp: Returning the filtered silks.");
        return silk_dtos;
    }

    @Override
    public ArrayList<Silk_dto> getByTypeSize(String type, int size) {
        log.info("Silk_serviceimp: Received a request to silks wrt type-size filter, type = {}, size = {}", type, size);
        ArrayList<Silk_dto> silk_dtos = new ArrayList<>();
        Iterable<SilkEntity> silkEntities = silk_Repo.findAll();
        for (SilkEntity silkEntity : silkEntities) {
            if (silkEntity.getSize() == size && silkEntity.getType().toString().equals(type)) {
                silk_dtos.add(convert_entity_to_dto(silkEntity));
            }
        }
        log.info("Silk_serviceimp: Returning the filtered silks.");
        return silk_dtos;
    }

//    @Override
//    public String add(SilkEntity silkEntity) {
//        silk_Repo.save(silkEntity);
//        return "Added";
//    }
//
//    @Override
//    public ArrayList<Silk_dto> getByTypeAndSize(String type, int size) {
//        ArrayList<Silk_dto> silk_dtos = new ArrayList<>();
//        Iterable<SilkEntity> silkEntities = silk_Repo.findAll();
//        for (SilkEntity silkEntity : silkEntities) {
//            if (silkEntity.getType().toString().equals(type) && silkEntity.getSize() == size) {
//                silk_dtos.add(convert_entity_to_dto(silkEntity));
//            }
//        }
//        return silk_dtos;
//    }

    @Override
    public Silk_dto createSilk(String name, int price, int quantity, SilkEnum silkEnum, Blob blob, int size, String colour, KurtaEnum kurtaEnum) {
        log.info("Silk_serviceimp: Received a request to create silk with parameters: name = {}, price = {}, quantity = {}," +
                "silk type = {}, size = {}, colour = {}, kurta type = {}", name, price, quantity, silkEnum, size, colour, kurtaEnum);
        KurtaEntity kurtaEntity = new KurtaEntity();
        SilkEntity silkEntity = new SilkEntity();
        kurtaEntity.setSize(size);
        kurtaEntity.setPrice(price);
        kurtaEntity.setKurtaEnum(kurtaEnum);
        kurtaEntity.setQuantity(quantity);
        kurtaEntity.setImage(blob);
        silkEntity.setType(silkEnum);
        kurtaEntity.setName(name);
        kurtaEntity.setColour(colour);
        Silk_dto silk_dto = new Silk_dto();
        silkEntity.setKurtaEntity(kurtaEntity);
        kurtaRepo.save(kurtaEntity);
        SilkEntity savedSilkEntity = silk_Repo.save(silkEntity);
        silk_dto.setId(savedSilkEntity.getId());
        log.info("Silk_serviceimp: Created silk = {}", silk_dto);
        return silk_dto;
    }

    @Override
    public ArrayList<Silk_dto> getSilkByName(String name) {
        log.info("Silk_serviceimp: Received a request to filter silks by name filter, name = {}", name);
        Iterable<KurtaEntity> kurtaEntities = kurtaRepo.findAllByNameContaining(name);
        ArrayList<Silk_dto> silk_dtos = new ArrayList<>();
        for (KurtaEntity kurtaEntity : kurtaEntities) {
            if (kurtaEntity.getKurtaEnum().equals(KurtaEnum.SILK)) {
                silk_dtos.add(convert_entity_to_dto(kurtaEntity.getSilkEntity()));
            }
        }
        log.info("Silk_serviceimp: Returning the filtered silks.");
        return silk_dtos;
    }

    @Override
    public ArrayList<Silk_dto> getSilkByColour(String colour) {
        log.info("Silk_serviceimp: Received a request to filter silks by colour filter, colour = {}", colour);
        ArrayList<Silk_dto> silk_dtos = new ArrayList<>();
        Iterable<SilkEntity> silkEntities = silk_Repo.findAll();
        for (SilkEntity silkEntity : silkEntities) {
            if (silkEntity.getColour().equals(colour)) {
                silk_dtos.add(convert_entity_to_dto(silkEntity));
            }
        }
        log.info("Silk_serviceimp: Returning the filtered silks.");
        return silk_dtos;
    }

    @Override
    public ArrayList<Silk_dto> getSilkByColourAndtype(String type, String colour) {
        log.info("Silk_serviceimp: Received a request to filter silks by type-colour filter, type = {}, colour = {}", type, colour);
        SilkEnum silkEnum = SilkEnum.valueOf(type);
        ArrayList<Silk_dto> silk_dtos = new ArrayList<>();
        Iterable<SilkEntity> silkEntities = silk_Repo.findAll();
        for (SilkEntity silkEntity : silkEntities) {
            if (silkEntity.getColour().equals(colour) && silkEntity.getType().toString().equals(type)) {
                silk_dtos.add(convert_entity_to_dto(silkEntity));
            }
        }
        log.info("Silk_serviceimp: Returning the filtered silks.");
        return silk_dtos;
    }

    @Override
    public Silk_dto updateSilk(Long id, int quantity) {
        log.info("Silk_serviceimp: Received a request to update silk with id = {} by quantity = {}", id, quantity);
        SilkEntity silkEntity = silk_Repo.findById(id).get();
        silkEntity.setQuantity(quantity+silkEntity.getQuantity());
        log.info("Silk_serviceimp: Updated silk = {}", silkEntity);
        return this.convert_entity_to_dto(silk_Repo.save(silkEntity));
    }

    @Override
    public ArrayList<Silk_dto> getByTypeColourSize(String type, String colour, int size) {
        log.info("Silk_serviceimp: Received a request to filter silks by type-colour-size filter, type = {}, " +
                "colour = {}, size = {}", type, colour, size);
        ArrayList<Silk_dto> silk_dtos = new ArrayList<>();
        Iterable<SilkEntity> silkEntities = silk_Repo.findAll();
        for (SilkEntity silkEntity : silkEntities) {
            if (silkEntity.getType().toString().equals(type) && silkEntity.getColour().equals(colour) && silkEntity.getSize() == size) {
                silk_dtos.add(convert_entity_to_dto(silkEntity));
            }
        }
        log.info("Silk_serviceimp: Returning the filtered silks.");
        return silk_dtos;
    }

    @Override
    public ArrayList<Silk_dto> getBySizeColour(int size, String colour) {
        log.info("Silk_serviceimp: Received a request to get silks wrt size-colour filter, size = {}, colour = {}", size,
                colour);
        ArrayList<Silk_dto> silk_dtos = new ArrayList<>();
        Iterable<SilkEntity> silkEntities = silk_Repo.findAll();
        for (SilkEntity silkEntity : silkEntities) {
            if (silkEntity.getSize() == size && silkEntity.getColour().equals(colour)) {
                silk_dtos.add(convert_entity_to_dto(silkEntity));
            }
        }
        log.info("Silk_serviceimp: Returning the filtered silks.");
        return silk_dtos;
    }

    @Override
    public void deleteById(Long id) {
        log.info("Silk_serviceimp: Received a request to delete silk with id = {}", id);
        KurtaEntity kurtaEntity = kurtaRepo.findById(id).get();
        kurtaRepo.delete(kurtaEntity);
        log.info("Silk_serviceimp: Deleted silk with id = {}", id);
    }
}
