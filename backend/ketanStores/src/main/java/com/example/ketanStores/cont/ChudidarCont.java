package com.example.ketanStores.cont;


import com.example.ketanStores.dto.ChudidarDto;
import com.example.ketanStores.enums.ChudidarEnum;
import com.example.ketanStores.service.ChudidarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@RestController
public class ChudidarCont {

    private final ChudidarService chudidarService;

    public ChudidarCont(ChudidarService chudidarService) {
        this.chudidarService = chudidarService;
    }


    @PostMapping("/churidar")
    public ResponseEntity<?> postChuridar(@RequestParam("name") String name, @RequestParam("img") MultipartFile file, @RequestParam("price") int price, @RequestParam("quantity") int quantity, @RequestParam("type") String chudidar,@RequestParam("size") int size) throws IOException, SQLException {
        ChudidarDto chudidarDto=new ChudidarDto();
        byte[] bytes = file.getBytes();
        Blob blob = new SerialBlob(bytes);
        ChudidarEnum chudidarEnum=ChudidarEnum.valueOf(chudidar);
        chudidarDto=chudidarService.createChuridar(name,price,quantity,chudidarEnum,blob,size);
        if(chudidarDto==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(chudidarDto);
    }
    @GetMapping("/churidar/{id}")
    public ResponseEntity<?> getChuridar(@PathVariable Long id){
        ChudidarDto chudidarDto=chudidarService.getChudidar(id);
        if(chudidarDto==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(chudidarDto);
    }

}
