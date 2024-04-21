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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ChudidarDto chudidarDto = chudidarService.getChudidar(id);
        if (chudidarDto==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(chudidarDto);
    }
    @GetMapping("/churidars")
    public ResponseEntity<?> getChuridars(){
        List<ChudidarDto> chudidarDtos=chudidarService.getChuridars();
        if(chudidarDtos.isEmpty()){
            List<ChudidarDto> chudidarDtos1=new ArrayList<>();
            return ResponseEntity.ok().body(chudidarDtos1);
        }
        else{
            return ResponseEntity.ok().body(chudidarDtos);
        }
    }
    @GetMapping("/chudiar/{type}")
    public ResponseEntity<?> getByType(@PathVariable String type){
        List<ChudidarDto> chudidarDtos=chudidarService.getChudidarByType(type);
        if(chudidarDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(chudidarDtos);
    }
    @PutMapping("/chudidar/update/{id}")
    public ResponseEntity<?> updateChudidar(@PathVariable Long id,@RequestParam("price") int price,@RequestParam("quantity") int quantity) {
        ChudidarDto chudidarDto = chudidarService.updateChudidar(id,price,quantity);
        if (chudidarDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(chudidarDto);
    }
    @GetMapping("/chudidar/{type}/filter/{size}")
    public ResponseEntity<?> getByTypeSize(@PathVariable String type,@PathVariable int size) {
        List<ChudidarDto> chudidarDtos = chudidarService.getChudidarByTypeandSize(type,size);
        if (chudidarDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(chudidarDtos);
    }
    @DeleteMapping("/chudidar/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        chudidarService.deleteById(id);
        Map<String,String> deletemap=new HashMap<>();
        deletemap.put("message","deleted successfully"+id);
        return ResponseEntity.ok().body(deletemap);

    }
    @GetMapping("/chudidar/search/{name}")
    public ResponseEntity<?> search(@PathVariable String name){
        List<ChudidarDto> chudidarDtos=chudidarService.getChudidarByName(name);
        if(chudidarDtos.isEmpty()){
            List<ChudidarDto> chudidarDtos1=new ArrayList<>();
            return ResponseEntity.ok().body(chudidarDtos1);
        }
        return ResponseEntity.ok().body(chudidarDtos);

    }

}
