package com.example.ketanStores.service;

import com.example.ketanStores.dto.ChudidarDto;
import com.example.ketanStores.dto.Others_dto;
import com.example.ketanStores.entity.ChudidarEntity;
import com.example.ketanStores.entity.OthersEntity;
import com.example.ketanStores.enums.ChudidarEnum;
import com.example.ketanStores.enums.OthersEnum;
import com.example.ketanStores.repository.Others_repo;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Others_serviceimp implements Other_service{
    @Autowired
    Others_repo othersRepo;

    public static String blobToBase64(Blob blob) {
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
    public Others_dto getOtherById(Long id) {
        Others_dto othersDto = new Others_dto();
        Optional<OthersEntity> othersEntity = othersRepo.findById(id);
        if (othersEntity.isPresent()) {
            othersDto.setId(othersEntity.get().getId());
            othersDto.setSize(othersEntity.get().getSize());
            othersDto.setPrice(othersEntity.get().getPrice());
            othersDto.setQuantity(othersEntity.get().getQuantity());
            othersDto.setImage(Others_serviceimp.blobToBase64(othersEntity.get().getImage()));
            othersDto.setTypeName(othersEntity.get().getType());
            othersDto.setAvailable(othersEntity.get().isAvailable());
            othersDto.setColour(othersEntity.get().getColour());
            return othersDto;
        }
        return null;
    }

    @Override
    public List<Others_dto> getOthers() {
        return othersRepo.findAll().stream().map(OthersEntity::getOtherDto).collect(Collectors.toList());
    }

    @Override
    public List<Others_dto> getOthersByType(String type) {
        OthersEnum othersEnum = OthersEnum.valueOf(type);
        return othersRepo.findAllByType(othersEnum).stream().map(OthersEntity::getOtherDto).collect(Collectors.toList());
    }

    @Override
    public Others_dto createOther(String name, int price, int quantity, OthersEnum othersEnum, Blob blob, int size, String colour) {
        OthersEntity othersEntity = new OthersEntity();
        othersEntity.setSize(size);
        othersEntity.setPrice(price);
        othersEntity.setAvailable(Boolean.TRUE);
        othersEntity.setQuantity(quantity);
        othersEntity.setImage(blob);
        othersEntity.setType(othersEnum);
        othersEntity.setName(name);
        othersEntity.setColour(colour);
        Others_dto othersDto = new Others_dto();
        OthersEntity savedOthers = othersRepo.save(othersEntity);
        othersDto.setId(savedOthers.getId());
        return othersDto;
    }

    @Override
    public Others_dto updateOther(Long id, int price, int quantity) {
        OthersEntity othersEntity = othersRepo.findById(id).get();
        othersEntity.setPrice(price);
        if(othersEntity.isAvailable()) {
            othersEntity.setQuantity(othersEntity.getQuantity() + quantity);
        }
        else{
            othersEntity.setAvailable(true);
            othersEntity.setQuantity(quantity);
        }
        OthersEntity savedone=othersRepo.save(othersEntity);
        Others_dto othersDto = new Others_dto();
        othersEntity.setId(savedone.getId());
        return othersDto;
    }

    @Override
    public List<Others_dto> getOtherByTypeandSize(String type, int size) {
        OthersEnum othersEnum = OthersEnum.valueOf(type);
        return othersRepo.findAllByTypeAndSize(othersEnum,size).stream().map(OthersEntity::getOtherDto).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        OthersEntity othersEntity=othersRepo.findById(id).get();
        othersRepo.delete(othersEntity);
    }

    @Override
    public List<Others_dto> getOthersByName(String name) {
        return othersRepo.findAllByName(name).stream().map(OthersEntity::getOtherDto).collect(Collectors.toList());
    }

    @Override
    public List<Others_dto> getOtherByTypeandSizeandColour(String type, int size, String colour) {
        OthersEnum othersEnum = OthersEnum.valueOf(type);
        return othersRepo.findAllByTypeAndSizeAndColour(othersEnum,size,colour).stream().map(OthersEntity::getOtherDto).collect(Collectors.toList());
    }

    @Override
    public List<Others_dto> getOtherByTypeandColour(String type, String colour) {
        OthersEnum othersEnum = OthersEnum.valueOf(type);
        return othersRepo.findAllBYTypeAndColour(othersEnum,colour).stream().map(OthersEntity::getOtherDto).collect(Collectors.toList());
    }
}
