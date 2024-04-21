package com.example.ketanStores.cont;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.ketanStores.dto.ChudidarDto;
import com.example.ketanStores.dto.Silk_dto;
import com.example.ketanStores.enums.CottonEnum;
import com.example.ketanStores.enums.SilkEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ketanStores.dto.Cotton_dto;
import com.example.ketanStores.service.Cotton_service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;


@RestController
@RequestMapping("/kurta/cotton")
public class CottonCont {
    private final Cotton_service cotton_service;
    public CottonCont(Cotton_service cotton_service) {
        this.cotton_service = cotton_service;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getClothbyid(@PathVariable("id") Long id) {
        Cotton_dto cotton = cotton_service.getClothbyid(id);
        if (cotton == null) {
            return ResponseEntity.status(404).body("No data found");
        }
        return ResponseEntity.ok().body(cotton);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getall() {
        ArrayList<Cotton_dto> cotton = cotton_service.getall();
        if (cotton.isEmpty()) {
            return ResponseEntity.status(404).body("No data found");
        }
        return ResponseEntity.ok().body(cotton);
    }
    @GetMapping("/{type}")
    public ResponseEntity<?> getbytype(@PathVariable("type") String type) {
        ArrayList<Cotton_dto> cotton = cotton_service.getbytype(type);
        if (cotton.isEmpty()) {
            return ResponseEntity.status(404).body("No data found");
        }
        return ResponseEntity.ok().body(cotton);
    }
    @GetMapping("/size")
    public ResponseEntity<?> getbysize(@PathVariable("size") int size) {
        ArrayList<Cotton_dto> cotton = cotton_service.getbysize(size);
        if (cotton.isEmpty()) {
            return ResponseEntity.status(404).body("No data found");
        }
        return ResponseEntity.ok().body(cotton);
    }
    @PostMapping("/cotton")
    public ResponseEntity<?> postSilk(@RequestParam("name") String name, @RequestParam("img") MultipartFile file, @RequestParam("price") int price, @RequestParam("quantity") int quantity, @RequestParam("type") String type, @RequestParam("size") int size, @RequestParam("colour") String colour) throws IOException, SQLException {
        byte[] bytes = file.getBytes();
        Blob blob = new SerialBlob(bytes);
        CottonEnum cottonEnum = CottonEnum.valueOf(type);
        Cotton_dto cotton_dto = cotton_service.createCotton(name, price, quantity, cottonEnum, blob, size);
        if (cotton_dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(cotton_dto);
    }
    @GetMapping("/cotton/search/{name}")
    public ResponseEntity<?> search(@PathVariable String name){
        List<Cotton_dto> cottonDtos=cotton_service.getCottonByName(name);
        return ResponseEntity.ok().body(cottonDtos);
    }
}
