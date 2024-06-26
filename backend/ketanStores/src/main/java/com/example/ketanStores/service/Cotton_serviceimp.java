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
import com.example.ketanStores.enums.KurtaEnum;
import com.example.ketanStores.repository.Kurta_repo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ketanStores.dto.Cotton_dto;
import com.example.ketanStores.entity.CottonEntity;
import com.example.ketanStores.enums.CottonEnum;
import com.example.ketanStores.repository.Cotton_repo;

@Slf4j
@Service
public class Cotton_serviceimp implements Cotton_service{

    @Autowired
    private Cotton_repo cotton_Repo;

    @Autowired
    private Kurta_repo kurtaRepo;

    @Override
    public Cotton_dto getClothbyid(Long id) {
        log.info("Cotton_serviceimp: Received a request to get cotton with id = {}", id);
        Optional<CottonEntity> cotton = cotton_Repo.findById(id);
        if(cotton.isPresent()){
            log.info("Cotton_serviceimp: Returning cotton = {}", cotton);
            return convert_entity_to_dto(cotton.get());
        }
        log.error("Cotton_serviceimp: Unable to find cotton with id = {}", id);
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
        log.info("Cotton_serviceimp: Received a request for retrieving cotton wrt type-size filter, type = {}, size = {}",
                type, size);
        ArrayList<Cotton_dto> cotton_dtos = new ArrayList<>();
        Iterable<CottonEntity> cottonDtos = cotton_Repo.findAll();
        for (CottonEntity cottonEntity : cottonDtos) {
            if (cottonEntity.getType().toString().equals(type) && cottonEntity.getSize() == size) {
                cotton_dtos.add(convert_entity_to_dto(cottonEntity));
            }
        }
        log.info("Cotton_serviceimp: Returning the list of cottons based on type-size filter.");
        return cotton_dtos;
    }

    @Override
    public Cotton_dto createCotton(String name, int price, int quantity, CottonEnum cottonEnum, Blob blob, int size, String colour, KurtaEnum kurtaEnum) {
        log.info("Cotton_serviceimp: Received a request for creating a cotton with parameters : name = {}, price = {}" +
                "quantity = {}, type of cotton = {}, size = {}, colour = {}, type of kurta = {}", name, price, quantity,
                cottonEnum, size, colour, kurtaEnum);
        KurtaEntity kurtaEntity = new KurtaEntity();
        CottonEntity cottonEntity = new CottonEntity();
        kurtaEntity.setSize(size);
        kurtaEntity.setPrice(price);
        kurtaEntity.setKurtaEnum(kurtaEnum);
        kurtaEntity.setQuantity(quantity);
        kurtaEntity.setImage(blob);
        kurtaEntity.setColour(colour);
        cottonEntity.setType(cottonEnum);
        kurtaEntity.setName(name);

        Cotton_dto cotton_dto = new Cotton_dto();
        cottonEntity.setKurtaEntity(kurtaEntity);
        kurtaRepo.save(kurtaEntity);
        log.info("Cotton_serviceimp: Saved kurta in kurta repository");
        CottonEntity savedCottonEntity = cotton_Repo.save(cottonEntity);
        log.info("Cotton_serviceimp: Saved cotton in cotton repository");

        cotton_dto.setId(savedCottonEntity.getId());
        return cotton_dto;
    }

    @Override
    public ArrayList<Cotton_dto> getCottonByName(String name) {
        log.info("Cotton_serviceimp: Received a request for retrieving cottons wrt name filter, name = {}", name);
        ArrayList<Cotton_dto> cotton_dtos = new ArrayList<>();
        Iterable<KurtaEntity> kurtaEntities = kurtaRepo.findAllByNameContaining(name);
        for (KurtaEntity kurtaEntity : kurtaEntities) {
            if (kurtaEntity.getKurtaEnum().equals(KurtaEnum.COTTON)) {
                cotton_dtos.add(convert_entity_to_dto(kurtaEntity.getCottonEntity()));
            }
        }
        log.info("Cotton_serviceimp: Returning the matching cottons.");
        return cotton_dtos;
    }

    @Override
    public ArrayList<Cotton_dto> getCottonByColour(String colour) {
        log.info("Cotton_serviceimp: Received a request for retrieving cottons wrt colour filter, colour = {}", colour);
        ArrayList<Cotton_dto> cotton_dtos = new ArrayList<>();
        Iterable<CottonEntity> cottonEntities = cotton_Repo.findAll();
        for (CottonEntity cottonEntity : cottonEntities) {
            if (cottonEntity.getColour().equals(colour)) {
                cotton_dtos.add(convert_entity_to_dto(cottonEntity));
            }
        }
        log.info("Cotton_serviceimp: Returning the matching cottons.");
        return cotton_dtos;
    }

    @Override
    public ArrayList<Cotton_dto> getByTypeColour(String type, String colour) {
        log.info("Cotton_serviceimp: Received a request to retrieve cottons by type-colour filter, type = {}, colour = {}",
                type, colour);
        ArrayList<Cotton_dto> cotton_dtos = new ArrayList<>();
        Iterable<CottonEntity> cottonEntities = cotton_Repo.findAll();
        for (CottonEntity cottonEntity : cottonEntities) {
            if (cottonEntity.getColour().equals(colour) && cottonEntity.getType().toString().equals(type)) {
                cotton_dtos.add(convert_entity_to_dto(cottonEntity));
            }
        }
        log.info("Cotton_serviceimp: Returning the matching cottons.");
        return cotton_dtos;
    }

    @Override
    public Cotton_dto updateCotton(Long id, int quantity) {
        log.info("Cotton_serviceimp: Received a request to update the cotton with id = {} with quantity = {}", id, quantity);
        CottonEntity cottonEntity = cotton_Repo.findById(id).get();
        cottonEntity.setQuantity(quantity+cottonEntity.getQuantity());
        log.info("Cotton_serviceimp: Updated cotton = {}", cottonEntity);
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
        log.info("Cotton_serviceimp: Received a request to get all cottons");
        ArrayList<Cotton_dto> cotton_dtos = new ArrayList<>();
        Iterable<CottonEntity> cottons = cotton_Repo.findAll();
        for(CottonEntity cotton : cottons){
            cotton_dtos.add(convert_entity_to_dto(cotton));
        }
        System.out.println(cotton_dtos);
        log.info("Cotton_serviceimp: Getting all cottons.");
        return cotton_dtos;
    }

    @Override
    public ArrayList<Cotton_dto> getbytype(String type) {
        log.info("Cotton_serviceimp: Received a request to get cottons wrt type filter.");
        ArrayList<Cotton_dto> cotton_dtos = new ArrayList<>();
        Iterable<CottonEntity> cottons = cotton_Repo.findAll();
        for(CottonEntity cotton : cottons){
            if(cotton.getType().toString().equals(type)){
                cotton_dtos.add(convert_entity_to_dto(cotton));
            }
        }
        log.info("Cotton_serviceimp: Getting all cottons.");
        return cotton_dtos;
    }

    @Override
    public ArrayList<Cotton_dto> getbysize(int size) {
        log.info("Cotton_serviceimp: Received a request to get cottons wrt size filter.");
        ArrayList<Cotton_dto> cotton_dtos = new ArrayList<>();
        Iterable<CottonEntity> cottons = cotton_Repo.findAll();
        for(CottonEntity cotton : cottons){
            if(cotton.getSize() == size){
                cotton_dtos.add(convert_entity_to_dto(cotton));
            }
        }
        log.info("Cotton_serviceimp: Returning the filtered cottons.");
        return cotton_dtos;
    }
    @Override
    public ArrayList<Cotton_dto> getByTypeColourSize(String type, String colour, int size) {
        log.info("Cotton_serviceimp: Received a request to get cottons wrt type-colour-size filter.");
        ArrayList<Cotton_dto> cotton_dtos = new ArrayList<>();
        Iterable<CottonEntity> cottonEntities = cotton_Repo.findAll();
        for (CottonEntity cottonEntity : cottonEntities) {
            if (cottonEntity.getType().toString().equals(type) && cottonEntity.getColour().equals(colour) && cottonEntity.getSize() == size) {
                cotton_dtos.add(convert_entity_to_dto(cottonEntity));
            }
        }
        log.info("Cotton_serviceimp: Returning the filtered cottons.");
        return cotton_dtos;
    }

    @Override
    public ArrayList<Cotton_dto> getByColourSize(int size, String colour) {
        log.info("Cotton_serviceimp: Received a request to get cottons wrt size-colour filter, size = {}, colour = {}",
                size, colour);
        ArrayList<Cotton_dto> cotton_dtos = new ArrayList<>();
        Iterable<CottonEntity> cottonEntities = cotton_Repo.findAll();
        for (CottonEntity cottonEntity : cottonEntities) {
            if (cottonEntity.getColour().equals(colour) && cottonEntity.getSize() == size) {
                cotton_dtos.add(convert_entity_to_dto(cottonEntity));
            }
        }
        log.info("Cotton_serviceimp: Returning the filtered cottons.");
        return cotton_dtos;
    }

    @Override
    public void deleteById(Long id) {
        log.info("Cotton_serviceimp: Received a request to delete cotton with id = {}", id);
        KurtaEntity kurtaEntity = kurtaRepo.findById(id).get();
        kurtaRepo.delete(kurtaEntity);
        log.info("Cotton_serviceimp: Deleted cotton with id = {}", id);
    }

    @Override
    public ArrayList<Cotton_dto> getByTypeSize(String type, int size) {
        log.info("Cotton_serviceimp: Received a request to get cottons wrt type-size filter, type = {}, size = {}",
                type, size);
        ArrayList<Cotton_dto> cotton_dtos = new ArrayList<>();
        Iterable<CottonEntity> cottonEntities = cotton_Repo.findAll();
        for (CottonEntity cottonEntity : cottonEntities) {
            if (cottonEntity.getType().toString().equals(type) && cottonEntity.getSize() == size) {
                cotton_dtos.add(convert_entity_to_dto(cottonEntity));
            }
        }
        log.info("Cotton_serviceimp: Returning the filtered cottons.");
        return cotton_dtos;
    }
}
