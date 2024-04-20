package com.example.ketanStores.cont;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ketanStores.dto.Silk_dto;
import com.example.ketanStores.entity.SilkEntity;
import com.example.ketanStores.service.Silk_service;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



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
}
