package com.example.ketanStores.cont;
import com.example.ketanStores.enums.SilkEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ketanStores.dto.Silk_dto;
import com.example.ketanStores.entity.SilkEntity;
import com.example.ketanStores.service.Silk_service;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;


@RestController
@RequestMapping("/kurta/silk")
public class SilkCont {
    private final Silk_service silk_service;
    public SilkCont(Silk_service silk_service) {
        this.silk_service = silk_service;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getClothbyid(@PathVariable("id") Long id) {
        Silk_dto silk = silk_service.getClothbyid(id);
        if (silk == null) {
            return ResponseEntity.status(404).body("No data found");
        }
        return ResponseEntity.ok().body(silk);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getall() {
        ArrayList<Silk_dto> silk = silk_service.getall();
        if (silk.isEmpty()) {
            return ResponseEntity.status(404).body("No data found");
        }
        return ResponseEntity.ok().body(silk);
    }
    @GetMapping("/{type}")
    public ResponseEntity<?> getbytype(@PathVariable("type") String type) {
        ArrayList<Silk_dto> silk = silk_service.getbytype(type);
        if (silk.isEmpty()) {
            return ResponseEntity.status(404).body("No data found");
        }
        return ResponseEntity.ok().body(silk);
    }
    @GetMapping("/size")
    public ResponseEntity<?> getbysize(@RequestParam("size") int size) {
        ArrayList<Silk_dto> silk = silk_service.getbysize(size);
        if (silk.isEmpty()) {
            return ResponseEntity.status(404).body("No data found");
        }
        return ResponseEntity.ok().body(silk);
    }
    @PostMapping("/silk")
    public ResponseEntity<?> postSilk(@RequestParam("name") String name, @RequestParam("img") MultipartFile file, @RequestParam("price") int price, @RequestParam("quantity") int quantity, @RequestParam("type") String type, @RequestParam("size") int size, @RequestParam("colour") String colour) throws IOException, SQLException {
        byte[] bytes = file.getBytes();
        Blob blob = new SerialBlob(bytes);
        SilkEnum silkEnum = SilkEnum.valueOf(type);
        Silk_dto silk_dto = silk_service.createSilk(name, price, quantity, silkEnum, blob, size);
        if (silk_dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(silk_dto);
    }
    @GetMapping("/silk/search/{name}")
    public ResponseEntity<?> search(@PathVariable String name) {
        List<Silk_dto> silk_dtos = silk_service.getSilkByName(name);
        return ResponseEntity.ok().body(silk_dtos);
    }
}
