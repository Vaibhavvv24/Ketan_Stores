package com.example.ketanStores.entity;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;

import com.example.ketanStores.dto.ChudidarDto;
import com.example.ketanStores.dto.Others_dto;
import com.example.ketanStores.enums.OthersEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OthersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private OthersEnum type;
    private int price;
    private int size;
    private int quantity;
    private boolean available;
    @Column(columnDefinition = "longblob")
    private Blob image;
    private String name;
    private String colour;

    public String blobToBase64(Blob blob) {
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


        public Others_dto getOtherDto() {
        Others_dto othersDto =new Others_dto();
        othersDto.setPrice(price);
        othersDto.setQuantity(quantity);
        othersDto.setId(id);
        othersDto.setSize(size);
        othersDto.setName(name);
        othersDto.setAvailable(available);
        othersDto.setImage(blobToBase64(image));
        othersDto.setTypeName(type);
        return othersDto;
    }
}
