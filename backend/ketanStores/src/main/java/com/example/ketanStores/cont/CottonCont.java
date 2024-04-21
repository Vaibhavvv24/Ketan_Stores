package com.example.ketanStores.cont;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.ketanStores.dto.Cotton_dto;
import com.example.ketanStores.service.Cotton_service;
import org.springframework.web.bind.annotation.RequestMapping;


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
}
