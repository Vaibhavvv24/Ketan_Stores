package com.example.ketanStores.cont;

import com.example.ketanStores.dto.ChudidarDto;
import com.example.ketanStores.dto.Others_dto;
import com.example.ketanStores.enums.OthersEnum;
import com.example.ketanStores.repository.Others_repo;
import com.example.ketanStores.service.Other_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OthersCont {
    @Autowired
    Other_service otherService;

    @PostMapping("/other")
    public ResponseEntity<?> postOther(@RequestParam("name") String name, @RequestParam("img") MultipartFile file, @RequestParam("price") int price, @RequestParam("quantity") int quantity, @RequestParam("type") String type, @RequestParam("size") int size, @RequestParam("colour") String colour) throws IOException, SQLException {
        byte[] bytes = file.getBytes();
        Blob blob = new SerialBlob(bytes);
        OthersEnum othersEnum = OthersEnum.valueOf(type);
        Others_dto othersDto = otherService.createOther(name, price, quantity, othersEnum, blob, size, colour);
        if (othersDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(othersDto);
    }

    @GetMapping("/other/{id}")
    public ResponseEntity<?> getOther(@PathVariable Long id){
        Others_dto othersDto = otherService.getOtherById(id);
        if (othersDto==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(othersDto);
    }

    @GetMapping("/others")
    public ResponseEntity<?> getOthers(){
        List<Others_dto> othersDtos=otherService.getOthers();
        if(othersDtos.isEmpty()){
            List<Others_dto> othersDtos1=new ArrayList<>();
            return ResponseEntity.ok().body(othersDtos1);
        }
        else{
            return ResponseEntity.ok().body(othersDtos);
        }
    }

    @GetMapping("/other/{type}")
    public ResponseEntity<?> getByType(@PathVariable String type){
        List<Others_dto> othersDtos=otherService.getOthersByType(type);
        if(othersDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(othersDtos);
    }

    @PutMapping("/other/update/{id}")
    public ResponseEntity<?> updateChudidar(@PathVariable Long id,@RequestParam("price") int price,@RequestParam("quantity") int quantity) {
        Others_dto othersDto = otherService.updateOther(id,price,quantity);
        if (othersDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(othersDto);
    }

    @GetMapping("/other/{type}/filter/{size}")
    public ResponseEntity<?> getByTypeSize(@PathVariable String type,@PathVariable int size) {
        List<Others_dto> othersDtos = otherService.getOtherByTypeandSize(type,size);
        if (othersDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(othersDtos);
    }
    @GetMapping("/others/search/{name}")
    public ResponseEntity<?> search(@PathVariable String name){
        List<ChudidarDto> chudidarDtos=otherService.getOthersByName(name);
        if(chudidarDtos.isEmpty()){
            List<ChudidarDto> chudidarDtos1=new ArrayList<>();
            return ResponseEntity.ok().body(chudidarDtos1);
        }
        return ResponseEntity.ok().body(chudidarDtos);

    }

    @DeleteMapping("/chudidar/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        otherService.deleteById(id);
        Map<String,String> deletemap=new HashMap<>();
        deletemap.put("message","deleted successfully : "+id);
        return ResponseEntity.ok().body(deletemap);
    }
}
