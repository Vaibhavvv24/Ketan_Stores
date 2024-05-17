package com.example.ketanStores.cont;
import com.example.ketanStores.dto.PutBody;
import com.example.ketanStores.enums.KurtaEnum;
import com.example.ketanStores.enums.SilkEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.example.ketanStores.dto.Silk_dto;
import com.example.ketanStores.service.Silk_service;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;


@Slf4j
@RestController
@RequestMapping("/kurta_silk")
@CrossOrigin(origins = "http://localhost:5173")
public class SilkCont {
    private final Silk_service silk_service;
    public SilkCont(Silk_service silk_service) {
        this.silk_service = silk_service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClothbyid(@PathVariable("id") Long id) {
        log.info("SilkCont: Received a request to get silk with id = {}", id);
        Silk_dto silk = silk_service.getClothbyid(id);
        if (silk == null) {
            log.error("SilkCont: Silk with id = {} was not found.", id);
            return ResponseEntity.status(404).body("No data found");
        }
        log.info("SilkCont: Found silk = {}", silk);
        return ResponseEntity.ok().body(silk);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getall() {
        log.info("SilkCont: Received a request to get all silks");
        ArrayList<Silk_dto> silk = silk_service.getall();
        log.info("SilkCont: Returning all the silks.");
        return ResponseEntity.ok().body(silk);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<?> getbytype(@PathVariable("type") String type) {
        log.info("SilkCont: Received a request to retrieve silks wrt type-filter, type = {}", type);
        ArrayList<Silk_dto> silk = silk_service.getbytype(type);
        log.info("SilkCont: Returning filtered silks.");
        return ResponseEntity.ok().body(silk);
    }

    @GetMapping("/size/{size}")
    public ResponseEntity<?> getbysize(@PathVariable("size") int size) {
        log.info("SilkCont: Received a request to retrieve silks wrt size-filter, size = {}", size);
        ArrayList<Silk_dto> silk = silk_service.getbysize(size);
        log.info("SilkCont: Returning the filtered silks.");
        return ResponseEntity.ok().body(silk);
    }

    @PostMapping("/silk")
    public ResponseEntity<?> postSilk(@RequestParam("name") String name, @RequestParam("img") MultipartFile file, @RequestParam("price") int price, @RequestParam("quantity") int quantity, @RequestParam("type") String type, @RequestParam("size") int size, @RequestParam("colour") String colour) throws IOException, SQLException {
        log.info("SilkCont: Received a request to post silk with parameters: name = {}, price = {}, quantity = {}, type = {}, size = {}, colour = {}",
                name, price, quantity, type, size, colour);
        byte[] bytes = file.getBytes();
        Blob blob = new SerialBlob(bytes);
        SilkEnum silkEnum = SilkEnum.valueOf(type);
        KurtaEnum kurtaEnum = KurtaEnum.SILK;
        Silk_dto silk_dto = silk_service.createSilk(name, price, quantity, silkEnum, blob, size, colour, kurtaEnum);
        if (silk_dto == null) {
            log.error("SilkCont: Error posting silk with parameters: name = {}, price = {}, quantity = {}, type = {}," +
                    "size = {}, colour = {}", name, price, quantity, type, size, colour);
            return ResponseEntity.notFound().build();
        }
        log.info("SilkCont: Posted silk = {}", silk_dto);
        return ResponseEntity.ok().body(silk_dto);
    }

    @GetMapping("/silk/search/{name}")
    public ResponseEntity<?> search(@PathVariable String name) {
        log.info("SilkCont: Received a request to retrieve silks wrt name-filter, name = {}", name);
        System.out.println(name);
        List<Silk_dto> silk_dtos = silk_service.getSilkByName(name);
        System.out.println(silk_dtos);
        log.info("SilkCont: Returning the filtered silks.");
        return ResponseEntity.ok().body(silk_dtos);
    }

    @GetMapping("/silk/colour_filter/{colour}")
    public ResponseEntity<?> getByColour(@PathVariable String colour) {
        log.info("SilkCont: Received a request for retrieving silks wrt colour-filter, colour = {}", colour);
        ArrayList<Silk_dto> silk_dtos = silk_service.getSilkByColour(colour);
        log.info("SilkCont: Returning the filtered silks.");
        return ResponseEntity.ok().body(silk_dtos);
    }

    @GetMapping("/silk/{type}/colour/{colour}")
    public ResponseEntity<?> getByTypeColour(@PathVariable String type, @PathVariable String colour) {
        log.info("SilkCont: Received a request to filter silks wrt type-colour filter, type = {}, colour = {}", type, colour);
        ArrayList<Silk_dto> silk_dtos = silk_service.getSilkByColourAndtype(type, colour);
        log.info("SilkCont: Returning the filtered silks.");
        return ResponseEntity.ok().body(silk_dtos);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSilk(@PathVariable Long id, @RequestBody PutBody putBody) {
        log.info("SilkCont: Received a request to update silk with id = {} by quantity = {}", id, Integer.parseInt(putBody.getQuantity()));
        int updated_Quantity = Integer.parseInt(putBody.getQuantity());
        Silk_dto silk_dto = silk_service.updateSilk(id, updated_Quantity);
        if (silk_dto == null) {
            log.error("SilkCont: Unable to find silk with id = {}", id);
            return ResponseEntity.notFound().build();
        }
        log.info("SilkCont: Updated silk = {}", silk_dto);
        return ResponseEntity.ok().body(silk_dto);
    }

    @GetMapping("/silk_tcs/{type}/colour/{colour}/size/{size}")
    public ResponseEntity<?> getByTypeSizeColour(@PathVariable String type, @PathVariable String colour, @PathVariable int size) {
        log.info("SilkCont: Received a request to retrieve silks wrt type-size-colour filter, type = {}, size = {}, colour = {}",
                type, size, colour);
        ArrayList<Silk_dto> silk_dtos = silk_service.getByTypeColourSize(type, colour, size);
        log.info("SilkCont: Returning the filtered silks.");
        return ResponseEntity.ok().body(silk_dtos);
    }

    @GetMapping("/silk_tc/{type}/size/{size}")
    public ResponseEntity<?> getByTypeSize(@PathVariable String type, @PathVariable int size) {
        log.info("SilkCont: Received a request to retrieve silks wrt type-size filter, type = {}, size = {}", type,
                size);
        ArrayList<Silk_dto> silk_dtos = silk_service.getByTypeSize(type, size);
        log.info("SilkCont: Returning the filtered silks.");
        return ResponseEntity.ok().body(silk_dtos);
    }

    @GetMapping("/silk_sc/{size}/colour/{colour}")
    public ResponseEntity<?> getBySizeColour(@PathVariable int size, @PathVariable String colour) {
        log.info("SilkCont: Received a request to retrieve silks wrt size-colour, size = {}, colour = {}", size, colour);
        ArrayList<Silk_dto> silk_dtos = silk_service.getBySizeColour(size, colour);
        log.info("SilkCont: Returning the filtered silks.");
        return ResponseEntity.ok().body(silk_dtos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        log.info("SilkCont: Received a request to delete silk with id = {}", id);
        silk_service.deleteById(id);
        HashMap<String, String> responseJSON = new HashMap<>();
        responseJSON.put("status", "deleted : " + id);
        log.info("SilkCont: Returning JSON = {}", responseJSON);
        return ResponseEntity.ok().body(responseJSON);
    }
}
