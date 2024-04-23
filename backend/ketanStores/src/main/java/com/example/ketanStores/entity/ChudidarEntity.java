package com.example.ketanStores.entity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;

import com.example.ketanStores.dto.ChudidarDto;
import com.example.ketanStores.enums.ChudidarEnum;
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
public class ChudidarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private ChudidarEnum type;
    private int size;
    private boolean available;
    private int quantity;
    private int price;
    private String name;
    @Column(columnDefinition = "longblob")
    private Blob image;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChudidarEnum getType_name() {
        return type;
    }

    public void setType_name(ChudidarEnum type_name) {
        type = type_name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ChudidarDto getChurdiarDto() {
        ChudidarDto chudidarDto=new ChudidarDto();
        chudidarDto.setPrice(price);
        chudidarDto.setQuantity(quantity);
        chudidarDto.setId(id);
        chudidarDto.setSize(size);
        chudidarDto.setName(name);
        chudidarDto.setImage(blobToBase64(image));
        chudidarDto.setType_name(type);
        return chudidarDto;
    }
}
