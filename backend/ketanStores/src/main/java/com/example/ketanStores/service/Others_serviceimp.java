package com.example.ketanStores.service;

import com.example.ketanStores.dto.Others_dto;
import com.example.ketanStores.entity.OthersEntity;
import com.example.ketanStores.enums.OthersEnum;
import com.example.ketanStores.repository.Others_repo;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;
import java.util.Optional;

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
    public Others_dto getOthersById(Long id) {
        Others_dto othersDto = new Others_dto();
        Optional<OthersEntity> othersEntity = othersRepo.findById(id);
        if (othersEntity.isPresent()) {
            othersDto.setId(othersEntity.get().getId());
            othersDto.setSize(othersEntity.get().getSize());
            othersDto.setPrice(othersEntity.get().getPrice());
            othersDto.setQuantity(othersEntity.get().getQuantity());
            othersDto.setImage(Others_serviceimp.blobToBase64(othersEntity.get().getImage()));
            othersDto.setType_name(othersEntity.get().getType_name());
            othersDto.setAvailable(othersEntity.get().isAvailable());
            return othersDto;
        }
        return null;
    }

    @Override
    public Others_dto postOthers(String name, int price, int quantity, OthersEnum othersEnum, Blob blob, int size, String colour) {
        OthersEntity othersEntity = new OthersEntity();
        othersEntity.setSize(size);
        othersEntity.setPrice(price);
        othersEntity.setAvailable(Boolean.TRUE);
        othersEntity.setQuantity(quantity);
        othersEntity.setImage(blob);
        othersEntity.setType_name(othersEnum);
        othersEntity.setName(name);
        othersEntity.setColour(colour);
        Others_dto othersDto = new Others_dto();
        OthersEntity savedOthers = othersRepo.save(othersEntity);
        othersDto.setId(savedOthers.getId());
        return othersDto;
    }

    @Override
    public Others_dto putOthers(String name, int price, int quantity, OthersEnum othersEnum, Blob blob, int size, String colour) {
        return null;
    }

    @Override
    public void deleteOthersById(Long id) {
        othersRepo.deleteById(id);
    }
}
