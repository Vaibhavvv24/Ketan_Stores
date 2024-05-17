package com.example.ketanStores.cont;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.ketanStores.dto.PutBody;
import com.example.ketanStores.enums.CottonEnum;
import com.example.ketanStores.enums.KurtaEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ketanStores.dto.Cotton_dto;
import com.example.ketanStores.service.Cotton_service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RestController
@RequestMapping("/kurta_cotton")
@CrossOrigin(origins = "http://localhost:5173")
public class CottonCont {
    private final Cotton_service cotton_service;

    public CottonCont(Cotton_service cotton_service) {
        this.cotton_service = cotton_service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClothbyid(@PathVariable Long id) {
        log.info("CottonCont: Received a request to retrieve cotton with id = {}", id);
        Cotton_dto cotton = cotton_service.getClothbyid(id);
        if (cotton == null) {
            log.error("CottonCont: Did not find cotton with id = {}", id);
            return ResponseEntity.notFound().build();
        }
        log.info("CottonCont: Returning Cotton = {}", cotton);
        return ResponseEntity.ok().body(cotton);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getall() {
        log.info("CottonCont: Received a request to retrieve all cottons");
        ArrayList<Cotton_dto> cotton = cotton_service.getall();
        return ResponseEntity.ok().body(cotton);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<?> getbytype(@PathVariable String type) {
        log.info("CottonCont: Received a reuqest to retrieve cottons of type = {}", type);
        ArrayList<Cotton_dto> cotton = cotton_service.getbytype(type);
        return ResponseEntity.ok().body(cotton);
    }

    @GetMapping("/size/{size}")
    public ResponseEntity<?> getbysize(@PathVariable int size) {
        log.info("CottonCont: Received a request to retrieve cottons of size = {}", size);
        ArrayList<Cotton_dto> cotton = cotton_service.getbysize(size);
        return ResponseEntity.ok().body(cotton);
    }

    @PostMapping("/cotton")
    public ResponseEntity<?> postCotton(@RequestParam("name") String name, @RequestParam("img") MultipartFile file,
            @RequestParam("price") int price, @RequestParam("quantity") int quantity, @RequestParam("type") String type,
            @RequestParam("size") int size, @RequestParam("colour") String colour) throws IOException, SQLException {
        log.info("CottonCont: Received a request to post cotton with parameters: name = {}, price = {}, quantity = {}, " +
                "type = {}, size = {}, colour = {}", name, price, quantity, type, size, colour);
        byte[] bytes = file.getBytes();
        Blob blob = new SerialBlob(bytes);
        CottonEnum cottonEnum = CottonEnum.valueOf(type);
        KurtaEnum kurtaEnum = KurtaEnum.COTTON;
        Cotton_dto cotton_dto = cotton_service.createCotton(name, price, quantity, cottonEnum, blob, size, colour, kurtaEnum);
        if (cotton_dto == null) {
            log.error("CottonCont: Failed to create a new cotton");
            return ResponseEntity.notFound().build();
        }
        log.info("CottonCont: Created cotton = {}", cotton_dto);
        return ResponseEntity.ok().body(cotton_dto);
    }

    @GetMapping("/cotton/search/{name}")
    public ResponseEntity<?> search(@PathVariable String name) {
        log.info("CottonCont: Received a request to retrieve cotton with name filter, name = {}", name);
        List<Cotton_dto> cottonDtos = cotton_service.getCottonByName(name);
        return ResponseEntity.ok().body(cottonDtos);
    }

    @GetMapping("/cotton/colour_filter/{colour}")
    public ResponseEntity<?> getByColour(@PathVariable String colour) {
        log.info("CottonCont: Received a request to retrieve cotton with colour filter, colour = {}", colour);
        ArrayList<Cotton_dto> cotton_dtos = cotton_service.getCottonByColour(colour);
        return ResponseEntity.ok().body(cotton_dtos);
    }

    @GetMapping("/cotton/{type}/colour/{colour}")
    public ResponseEntity<?> getByTypeColour(@PathVariable String type, @PathVariable String colour) {
        log.info("CottonCont: Received a request to retrieve cotton with type-colour filter, type = {}, " +
                "colour = {}", type, colour);
        ArrayList<Cotton_dto> cotton_dtos = cotton_service.getByTypeColour(type, colour);
        return ResponseEntity.ok().body(cotton_dtos);
    }
    @GetMapping("/cotton/{colour}/size/{size}")
    public ResponseEntity<?> getByColourSize(@PathVariable String colour, @PathVariable int size) {
        log.info("CottonCont: Received a request to retrieve cotton with colour-size filter, colour = {}, " +
                "size = {}", colour, size);
        ArrayList<Cotton_dto> cotton_dtos = cotton_service.getByColourSize(size, colour);
        return ResponseEntity.ok().body(cotton_dtos);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCotton(@PathVariable Long id, @RequestBody PutBody putBody) {
        log.info("CottonCont: Received a request to update cotton with id = {}", id);
        int updated_Quantity = Integer.parseInt(putBody.getQuantity());
        Cotton_dto cotton_dto = cotton_service.updateCotton(id, updated_Quantity);
        if (cotton_dto == null) {
            log.error("CottonCont: Was not able to find cotton with id = {}", id);
            return ResponseEntity.notFound().build();
        }
        log.info("CottonCont: Updated cotton = {}", cotton_dto);
        return ResponseEntity.ok().body(cotton_dto);
    }

    @GetMapping("/cotton_tcs/{type}/colour/{colour}/size/{size}")
    public ResponseEntity<?> getByTypeSizeColour(@PathVariable String type, @PathVariable String colour, @PathVariable int size) {
        log.info("CottonCont: Received a request to retrieve cotton by type-size-colour filter, type = {}, size = {}, " +
                "colour = {}", type, size, colour);
        ArrayList<Cotton_dto> cotton_dtos = cotton_service.getByTypeColourSize(type, colour, size);
        return ResponseEntity.ok().body(cotton_dtos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        log.info("CottonCont: Received a request to delete cotton with id = {}", id);
        cotton_service.deleteById(id);
        HashMap<String, String> responseJSON = new HashMap<>();
        responseJSON.put("status", "deleted : " + id);
        log.info("CottonCont: Returning = {}", responseJSON);
        return ResponseEntity.ok().body(responseJSON);
    }

    @GetMapping("cotton_ts/{type}/size/{size}")
    public ResponseEntity<?> getByTypeSize(@PathVariable String type, @PathVariable int size) {
        log.info("CottonCont: Received a request to filter by type-size filter, type = {}, size = {}", type, size);
        ArrayList<Cotton_dto> cotton_dtos = cotton_service.getByTypeSize(type, size);
        return ResponseEntity.ok().body(cotton_dtos);
    }
}