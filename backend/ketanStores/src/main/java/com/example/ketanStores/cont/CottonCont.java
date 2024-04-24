package com.example.ketanStores.cont;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.example.ketanStores.enums.CottonEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import com.example.ketanStores.dto.Cotton_dto;
import com.example.ketanStores.service.Cotton_service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/kurta_cotton")
@CrossOrigin(origins = "http://localhost:5173")
public class CottonCont {
    private final Cotton_service cotton_service;

    public CottonCont(Cotton_service cotton_service) {
        this.cotton_service = cotton_service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClothbyid(@PathVariable("id") Long id) {
        Cotton_dto cotton = cotton_service.getClothbyid(id);
        if (cotton == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(cotton);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getall() {
        ArrayList<Cotton_dto> cotton = cotton_service.getall();
        return ResponseEntity.ok().body(cotton);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<?> getbytype(@PathVariable("type") String type) {
        ArrayList<Cotton_dto> cotton = cotton_service.getbytype(type);
        return ResponseEntity.ok().body(cotton);
    }

    @GetMapping("/size/{size}")
    public ResponseEntity<?> getbysize(@PathVariable("size") int size) {
        ArrayList<Cotton_dto> cotton = cotton_service.getbysize(size);
        return ResponseEntity.ok().body(cotton);
    }

    @PostMapping("/cotton")
    public ResponseEntity<?> postSilk(@RequestParam("name") String name, @RequestParam("img") MultipartFile file,
            @RequestParam("price") int price, @RequestParam("quantity") int quantity, @RequestParam("type") String type,
            @RequestParam("size") int size, @RequestParam("colour") String colour) throws IOException, SQLException {
        byte[] bytes = file.getBytes();
        Blob blob = new SerialBlob(bytes);
        CottonEnum cottonEnum = CottonEnum.valueOf(type);
        Cotton_dto cotton_dto = cotton_service.createCotton(name, price, quantity, cottonEnum, blob, size, colour);
        if (cotton_dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(cotton_dto);
    }

    @GetMapping("/cotton/search/{name}")
    public ResponseEntity<?> search(@PathVariable String name) {
        List<Cotton_dto> cottonDtos = cotton_service.getCottonByName(name);
        return ResponseEntity.ok().body(cottonDtos);
    }

    @GetMapping("/cotton/colour_filter/{colour}")
    public ResponseEntity<?> getByColour(@PathVariable String colour) {
        ArrayList<Cotton_dto> cotton_dtos = cotton_service.getCottonByColour(colour);
        return ResponseEntity.ok().body(cotton_dtos);
    }

    @GetMapping("/cotton/{type}/colour/{colour}")
    public ResponseEntity<?> getByTypeColour(@PathVariable String type, @PathVariable String colour) {
        ArrayList<Cotton_dto> cotton_dtos = cotton_service.getByTypeColour(type, colour);
        return ResponseEntity.ok().body(cotton_dtos);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCotton(@PathVariable Long id, @RequestParam("quantity") int quantity) {
        Cotton_dto cotton_dto = cotton_service.updateCotton(id, quantity);
        if (cotton_dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(cotton_dto);
    }

    @GetMapping("/cotton_tcs/{type}/colour/{colour}/size/{size}")
    public ResponseEntity<?> getMethodName(@RequestParam String param) {
        ArrayList<Cotton_dto> cotton_dtos = cotton_service.getByTypeColourSize(param, param, Integer.parseInt(param));
        return ResponseEntity.ok().body(cotton_dtos);
    }

}
