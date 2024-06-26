package com.example.ketanStores.service;

import com.example.ketanStores.dto.Others_dto;
import com.example.ketanStores.entity.OthersEntity;
import com.example.ketanStores.enums.OthersEnum;
import com.example.ketanStores.repository.Others_repo;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class Others_serviceimp implements Other_service {
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
        log.info("Other_serviceimp: Received a request for getting Other by id = {}", id);
        Others_dto othersDto = new Others_dto();
        Optional<OthersEntity> othersEntity = othersRepo.findById(id);
        if (othersEntity.isPresent()) {
            othersDto.setId(othersEntity.get().getId());
            othersDto.setSize(othersEntity.get().getSize());
            othersDto.setPrice(othersEntity.get().getPrice());
            othersDto.setQuantity(othersEntity.get().getQuantity());
            othersDto.setImage(Others_serviceimp.blobToBase64(othersEntity.get().getImage()));
            othersDto.setTypeName(othersEntity.get().getType());
            othersDto.setColour(othersEntity.get().getColour());
            log.info("Other_serviceimp: Returning = {}", othersDto);
            return othersDto;
        }
        log.info("Other_serviceimp: Did not find Other by = {}", id);
        return null;
    }

    @Override
    public List<Others_dto> getOthers() {
        log.info("Other_serviceimp: Received a request for all Others.");
        return othersRepo.findAll().stream().map(OthersEntity::getOtherDto).collect(Collectors.toList());
    }

    @Override
    public List<Others_dto> getOthersByType(String type) {
        OthersEnum othersEnum = OthersEnum.valueOf(type);
        log.info("Other_serviceimp: Received a request for retrieving Others of type = {}", othersEnum);
        return othersRepo.findAllByType(othersEnum).stream().map(OthersEntity::getOtherDto)
                .collect(Collectors.toList());
    }

    @Override
    public Others_dto createOther(String name, int price, int quantity, OthersEnum othersEnum, Blob blob, int size, String colour) {
        log.info("Other_serviceimp: Received a request for creating Other with parameters name = {}, price = {}, quantity = {}, type = {}, size = {}, colour = {}", name, price, quantity, othersEnum, size, colour);
        OthersEntity othersEntity = new OthersEntity();
        othersEntity.setSize(size);
        othersEntity.setPrice(price);
        othersEntity.setQuantity(quantity);
        othersEntity.setImage(blob);
        othersEntity.setType(othersEnum);
        othersEntity.setName(name);
        othersEntity.setColour(colour);
        Others_dto othersDto = new Others_dto();
        OthersEntity savedOthers = othersRepo.save(othersEntity);
        othersDto.setId(savedOthers.getId());
        othersDto.setSize(savedOthers.getSize());
        othersDto.setPrice(savedOthers.getPrice());
        othersDto.setQuantity(savedOthers.getQuantity());
        othersDto.setTypeName(savedOthers.getType());
        othersDto.setName(savedOthers.getName());
        othersDto.setColour(savedOthers.getColour());
        log.info("Other_serviceimp: Returning = {}", othersDto);
        return othersDto;
    }

    @Override
    public Others_dto updateOther(Long id, int quantity) {
        log.info("Other_serviceimp: Received a request updating Other with id = {} with quantity = {}", id, quantity);
        OthersEntity othersEntity = othersRepo.findById(id).get();
        othersEntity.setQuantity(othersEntity.getQuantity() + quantity);
        OthersEntity savedone=othersRepo.save(othersEntity);
        Others_dto othersDto = new Others_dto();
        othersEntity.setId(savedone.getId());
        othersDto.setId(savedone.getId());
        othersDto.setQuantity(savedone.getQuantity());
        othersDto.setColour(savedone.getColour());
        othersDto.setSize(savedone.getSize());
        othersDto.setName(savedone.getName());
        othersDto.setPrice(savedone.getPrice());
        othersDto.setTypeName(savedone.getType());
        log.info("Other_serviceimp: After updating, returning = {}", othersDto);
        return othersDto;
    }

    @Override
    public List<Others_dto> getOtherByTypeandSize(String type, int size) {
        OthersEnum othersEnum = OthersEnum.valueOf(type);
        log.info("Other_serviceimp: Received a request for retrieving Others by type-size filter, type = {}, size = {}", type, size);
        return othersRepo.findAllByTypeAndSize(othersEnum, size).stream().map(OthersEntity::getOtherDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        OthersEntity othersEntity = othersRepo.findById(id).get();
        log.info("Other_serviceimp: Received a request for deleting Other of id = {}", id);
        othersRepo.delete(othersEntity);
    }

    @Override
    public List<Others_dto> getOthersByName(String name) {
        log.info("Other_serviceimp: Received a request for retrieving Others by name filter, name = {}", name);
        return othersRepo.findAllByNameContaining(name).stream().map(OthersEntity::getOtherDto).collect(Collectors.toList());
    }

    @Override
    public List<Others_dto> getOtherByTypeandSizeandColour(String type, int size, String colour) {
        OthersEnum othersEnum = OthersEnum.valueOf(type);
        log.info("Other_serviceimp: Received a request for retrieving Others by type-colour-size filter, type = {}, size = {}, colour = {}", type, size, colour);
        return othersRepo.findAllByTypeAndSizeAndColour(othersEnum, size, colour).stream()
                .map(OthersEntity::getOtherDto).collect(Collectors.toList());
    }

    @Override
    public List<Others_dto> getOtherByTypeandColour(String type, String colour) {
        OthersEnum othersEnum = OthersEnum.valueOf(type);
        log.info("Other_serviceimp: Received a request for retrieving Others by type-colour filter, type = {}, colour = {}", othersEnum, colour);
        return othersRepo.findAllByTypeAndColour(othersEnum, colour).stream().map(OthersEntity::getOtherDto)
                .collect(Collectors.toList());
    }
}
