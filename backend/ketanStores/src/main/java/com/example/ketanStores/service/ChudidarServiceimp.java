package com.example.ketanStores.service;

import com.example.ketanStores.dto.ChudidarDto;
import com.example.ketanStores.entity.ChudidarEntity;
import com.example.ketanStores.enums.ChudidarEnum;
import com.example.ketanStores.repository.Chudidar_repo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        log.info("ChudidarServiceimp: Received a request to create a Chudidar with parameters: name = {}, price = {}, quantity = {}, type = {}, size = {}", name, price, quantity, chudidarEnum, size);
        ChudidarEntity chudidarEntity=new ChudidarEntity();
        chudidarEntity.setSize(size);
        chudidarEntity.setPrice(price);
        chudidarEntity.setQuantity(quantity);
        chudidarEntity.setImage(blob);
        chudidarEntity.setType_name(chudidarEnum);
        chudidarEntity.setName(name);
        ChudidarDto chudidarDto=new ChudidarDto();
        ChudidarEntity savedChuridar=chudidarRepo.save(chudidarEntity);
        log.info("ChudidarServiceimp: Saved = {}", savedChuridar);
        chudidarDto.setId(savedChuridar.getId());
        chudidarDto.setSize(savedChuridar.getSize());
        chudidarDto.setName(savedChuridar.getName());
        chudidarDto.setPrice(savedChuridar.getPrice());
        chudidarDto.setType_name(savedChuridar.getType_name());
        chudidarDto.setQuantity(savedChuridar.getQuantity());
        log.info("ChudidarServiceimp: Returning = {}", chudidarDto);
        return chudidarDto;
    }

    @Override
    public ChudidarDto getChudidar(Long id) {
        log.info("ChudidarServiceimp: Received a request for retrieving a Chudidar with id = {}", id);
        ChudidarEntity chudidarEntity=chudidarRepo.findById(id).get();
        ChudidarDto chudidarDto=new ChudidarDto();
        chudidarDto.setSize(chudidarEntity.getSize());
        chudidarDto.setName(chudidarEntity.getName());
        chudidarDto.setType_name(chudidarEntity.getType_name());
        chudidarDto.setImage(blobToBase64(chudidarEntity.getImage()));
        chudidarDto.setPrice(chudidarEntity.getPrice());
        chudidarDto.setId(chudidarEntity.getId());
        chudidarDto.setQuantity(chudidarEntity.getQuantity());
        log.info("ChudidarServiceimp: Returning = {}", chudidarDto);
        return chudidarDto;
    }

    @Override
    public List<ChudidarDto> getChuridars() {
        log.info("ChudidarServiceimp: Received a request for retrieving all Chudidars.");
        return chudidarRepo.findAll().stream().map(ChudidarEntity::getChurdiarDto).collect(Collectors.toList());
    }

    @Override
    public List<ChudidarDto> getChudidarByType(String type) {
        ChudidarEnum chudidarEnum=ChudidarEnum.valueOf(type);
        log.info("ChudidarServiceimp: Received a request for retrieving Chudidar of type = {}", type);
        return chudidarRepo.findAllByType(chudidarEnum).stream().map(ChudidarEntity::getChurdiarDto).collect(Collectors.toList());
    }

    @Override
    public ChudidarDto updateChudidar(Long id, int quantity) {
        log.info("ChudidarServiceimp: Received a request for updating the Chudidar with id = {}, quantity = {}", id, quantity);
        ChudidarEntity chudidarEntity=chudidarRepo.findById(id).get();
        chudidarEntity.setQuantity(chudidarEntity.getQuantity() + quantity);
        ChudidarEntity savedone=chudidarRepo.save(chudidarEntity);
        ChudidarDto chudidarDto=new ChudidarDto();
        chudidarDto.setName(savedone.getName());
        chudidarDto.setPrice(savedone.getPrice());
        chudidarDto.setQuantity(savedone.getQuantity());
        chudidarDto.setSize(savedone.getSize());
        chudidarDto.setType_name(savedone.getType_name());
        chudidarDto.setId(savedone.getId());
        log.info("ChudidarServiceimp: After updating, returning = {}", chudidarDto);
        return chudidarDto;
    }

    @Override
    public List<ChudidarDto> getChudidarByTypeandSize(String type, int size) {
        ChudidarEnum chudidarEnum=ChudidarEnum.valueOf(type);
        log.info("ChudidarServiceimp: Received a request for retrieving Chudidars wrt type-size filter, type = {}, size = {}", type, size);
        return chudidarRepo.findAllByTypeAndSize(chudidarEnum,size).stream().map(ChudidarEntity::getChurdiarDto).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        ChudidarEntity chudidarEntity=chudidarRepo.findById(id).get();
        log.info("ChudidarServiceimp: Received a request for deleting Chudidar with id = {}", id);
        chudidarRepo.delete(chudidarEntity);
    }

    @Override
    public List<ChudidarDto> getChudidarByName(String name) {
        log.info("ChudidarServiceimp: Received a request for retrieving Chudidars wrt name filter, name = {}", name);
        return chudidarRepo.findAllByNameContaining(name).stream().map(ChudidarEntity::getChurdiarDto).collect(Collectors.toList());
    }
}
