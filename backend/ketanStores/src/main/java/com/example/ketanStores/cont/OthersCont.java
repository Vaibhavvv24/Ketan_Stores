package com.example.ketanStores.cont;

import com.example.ketanStores.dto.Others_dto;
import com.example.ketanStores.enums.OthersEnum;
import com.example.ketanStores.service.Other_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@RestController
@RequestMapping("/others")
public class OthersCont {
    @Autowired
    Other_service otherService;

    @GetMapping("/get/{id}")
    private Others_dto getOthersById(@PathVariable Long id) {
        try {
            return otherService.getOthersById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> postOther(@RequestParam("name") String name, @RequestParam("img") MultipartFile file, @RequestParam("price") int price, @RequestParam("quantity") int quantity, @RequestParam("type") String type, @RequestParam("size") int size, @RequestParam("colour") String colour) throws IOException, SQLException {
        byte[] bytes = file.getBytes();
        Blob blob = new SerialBlob(bytes);
        OthersEnum othersEnum = OthersEnum.valueOf(type);
        Others_dto othersDto = otherService.postOthers(name, price, quantity, othersEnum, blob, size, colour);
        if (othersDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(othersDto);
    }
}
