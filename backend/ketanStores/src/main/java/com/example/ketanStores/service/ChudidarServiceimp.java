package com.example.ketanStores.service;

import com.example.ketanStores.dto.ChudidarDto;
import com.example.ketanStores.entity.ChudidarEntity;
import com.example.ketanStores.enums.ChudidarEnum;
import com.example.ketanStores.repository.Chudidar_repo;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChudidarServiceimp implements ChudidarService{

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
    private final Chudidar_repo chudidarRepo;

    public ChudidarServiceimp(Chudidar_repo chudidarRepo) {
        this.chudidarRepo = chudidarRepo;
    }

    @Override
    public ChudidarDto createChuridar(String name, int price, int quantity, ChudidarEnum chudidarEnum, Blob blob,int size) {
        ChudidarEntity chudidarEntity=new ChudidarEntity();
        chudidarEntity.setSize(size);
        chudidarEntity.setPrice(price);
        chudidarEntity.setAvailable(Boolean.TRUE);
        chudidarEntity.setQuantity(quantity);
        chudidarEntity.setImage(blob);
        chudidarEntity.setType_name(chudidarEnum);
        chudidarEntity.setName(name);
        ChudidarDto chudidarDto=new ChudidarDto();
        ChudidarEntity savedChuridar=chudidarRepo.save(chudidarEntity);
        chudidarDto.setId(savedChuridar.getId());
        return chudidarDto;
    }

    @Override
    public ChudidarDto getChudidar(Long id) {
        ChudidarEntity chudidarEntity=chudidarRepo.findById(id).get();
        ChudidarDto chudidarDto=new ChudidarDto();
        if(chudidarEntity.getQuantity()==0){
            chudidarDto.setAvailable(false);
            chudidarDto.setSize(chudidarEntity.getSize());
            chudidarDto.setName(chudidarEntity.getName());
            chudidarDto.setType_name(chudidarEntity.getType_name());
            chudidarDto.setImage(blobToBase64(chudidarEntity.getImage()));
            chudidarDto.setPrice(chudidarEntity.getPrice());
            chudidarDto.setId(chudidarEntity.getId());
            chudidarDto.setQuantity(chudidarEntity.getQuantity());
        }
        else{
            chudidarDto.setAvailable(true);
            chudidarDto.setSize(chudidarEntity.getSize());
            chudidarDto.setName(chudidarEntity.getName());
            chudidarDto.setType_name(chudidarEntity.getType_name());
            chudidarDto.setImage(blobToBase64(chudidarEntity.getImage()));
            chudidarDto.setPrice(chudidarEntity.getPrice());
            chudidarDto.setId(chudidarEntity.getId());
            chudidarDto.setQuantity(chudidarEntity.getQuantity());
        }
        return chudidarDto;
    }

    @Override
    public List<ChudidarDto> getChuridars() {
        return chudidarRepo.findAll().stream().map(ChudidarEntity::getChurdiarDto).collect(Collectors.toList());
    }

    @Override
    public List<ChudidarDto> getChudidarByType(String type) {
        ChudidarEnum chudidarEnum=ChudidarEnum.valueOf(type);
        return chudidarRepo.findAllByType(chudidarEnum).stream().map(ChudidarEntity::getChurdiarDto).collect(Collectors.toList());
    }

    @Override
    public ChudidarDto updateChudidar(Long id, int price, int quantity) {
        ChudidarEntity chudidarEntity=chudidarRepo.findById(id).get();
        chudidarEntity.setPrice(price);
        if(chudidarEntity.isAvailable()) {
            chudidarEntity.setQuantity(chudidarEntity.getQuantity() + quantity);
        }
        else{
            chudidarEntity.setAvailable(true);
            chudidarEntity.setQuantity(quantity);
        }
        ChudidarEntity savedone=chudidarRepo.save(chudidarEntity);
        ChudidarDto chudidarDto=new ChudidarDto();
        chudidarEntity.setId(savedone.getId());
        return chudidarDto;
    }

    @Override
    public List<ChudidarDto> getChudidarByTypeandSize(String type, int size) {
        ChudidarEnum chudidarEnum=ChudidarEnum.valueOf(type);
        return chudidarRepo.findAllByTypeAndSize(chudidarEnum,size).stream().map(ChudidarEntity::getChurdiarDto).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        ChudidarEntity chudidarEntity=chudidarRepo.findById(id).get();
        chudidarRepo.delete(chudidarEntity);
    }

    @Override
    public List<ChudidarDto> getChudidarByName(String name) {
        return chudidarRepo.findAllByName(name).stream().map(ChudidarEntity::getChurdiarDto).collect(Collectors.toList());
    }
}
