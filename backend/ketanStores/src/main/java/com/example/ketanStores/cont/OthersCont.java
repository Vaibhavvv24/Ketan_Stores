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
@CrossOrigin(origins = "http://localhost:5173")
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
        return ResponseEntity.ok().body(othersDtos);
    }

    @GetMapping("/other/filter/{type}")
    public ResponseEntity<?> getByType(@PathVariable String type){
        List<Others_dto> othersDtos=otherService.getOthersByType(type);
        return ResponseEntity.ok().body(othersDtos);
    }

    @PutMapping("/other/update/{id}")
    public ResponseEntity<?> updateChudidar(@PathVariable Long id,@RequestParam("quantity") int quantity, @RequestParam("colour") String colour) {
        Others_dto othersDto = otherService.updateOther(id,quantity);
        if (othersDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(othersDto);
    }

    @GetMapping("/other/{type}/filter/{size}")
    public ResponseEntity<?> getByTypeSize(@PathVariable String type,@PathVariable int size) {
        List<Others_dto> othersDtos = otherService.getOtherByTypeandSize(type,size);
        return ResponseEntity.ok().body(othersDtos);
    }
    @GetMapping("/other/{type}/colour/{colour}")
    public ResponseEntity<?> getByTypeColour(@PathVariable String type,@PathVariable String colour) {
        List<Others_dto> othersDtos = otherService.getOtherByTypeandColour(type,colour);
        return ResponseEntity.ok().body(othersDtos);
    }
    @GetMapping("/other/{type}/filter/{size}/colour/{colour}")
    public ResponseEntity<?> getByTypeSizeColour(@PathVariable String type,@PathVariable int size,@PathVariable String colour) {
        List<Others_dto> othersDtos = otherService.getOtherByTypeandSizeandColour(type,size,colour);
        return ResponseEntity.ok().body(othersDtos);
    }
    @GetMapping("/others/search/{name}")
    public ResponseEntity<?> search(@PathVariable String name){
        List<Others_dto> othersDtos=otherService.getOthersByName(name);
        return ResponseEntity.ok().body(othersDtos);
    }

    @DeleteMapping("/others/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        otherService.deleteById(id);
        Map<String,String> deletemap=new HashMap<>();
        deletemap.put("message","deleted successfully : "+id);
        return ResponseEntity.ok().body(deletemap);
    }

}
