package com.example.ketanStores.cont;

import com.example.ketanStores.dto.Others_dto;
import com.example.ketanStores.dto.PutBody;
import com.example.ketanStores.enums.OthersEnum;
import com.example.ketanStores.service.Other_service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class OthersCont {
    @Autowired
    Other_service otherService;

    @PostMapping("/other")
    public ResponseEntity<?> postOther(@RequestParam("name") String name, @RequestParam("img") MultipartFile file, @RequestParam("price") int price, @RequestParam("quantity") int quantity, @RequestParam("type") String type, @RequestParam("size") int size, @RequestParam("colour") String colour) throws IOException, SQLException {
        log.info("OthersCont: Received a request to post Other with parameters: name = {}, price = {}, quantity = {}, type = {}, size = {}, colour = {}", name, price, quantity, type, size, colour);
        byte[] bytes = file.getBytes();
        Blob blob = new SerialBlob(bytes);
        OthersEnum othersEnum = OthersEnum.valueOf(type);
        Others_dto othersDto = otherService.createOther(name, price, quantity, othersEnum, blob, size, colour);
        if (othersDto == null) {
            log.error("OthersCont: Failed to create Ohter with parameters: name = {}, price = {}, quantity = {}, type = {}, size = {}, colour = {}", name, price, quantity, type, size, colour);
            return ResponseEntity.badRequest().build();
        }
        log.info("OthersCont: Created Other successfully. Created = {}", othersDto);
        return ResponseEntity.ok().body(othersDto);
    }

    @GetMapping("/other/{id}")
    public ResponseEntity<?> getOther(@PathVariable Long id){
        log.info("OthersCont: Recevied a request for retrieving other with id = {}", id);
        Others_dto othersDto = otherService.getOtherById(id);
        if (othersDto==null) {
            log.error("OthersCont: Failed to retrieve Other with id = {}", id);
            return ResponseEntity.notFound().build();
        }
        log.info("OthersCont: Retrieved = {}", othersDto);
        return ResponseEntity.ok().body(othersDto);
    }

    @GetMapping("/others")
    public ResponseEntity<?> getOthers(){
        log.info("OthersCont: Received a request to retrieve all Others.");
        List<Others_dto> othersDtos=otherService.getOthers();
        log.info("Retrieved = {}", othersDtos);
        return ResponseEntity.ok().body(othersDtos);
    }

    @GetMapping("/other/filter/{type}")
    public ResponseEntity<?> getByType(@PathVariable String type){
        log.info("OthersCont: Received a request to retrieve Others filtered wrt type, type = {}", type);
        List<Others_dto> othersDtos=otherService.getOthersByType(type);
        log.info("OthersCont: Retrieved = {}", othersDtos);
        return ResponseEntity.ok().body(othersDtos);
    }

    @PutMapping("/other/update/{id}")
    public ResponseEntity<?> updateChudidar(@PathVariable Long id, @RequestBody PutBody putBody) {
        log.info("OthersCont: Received a request for updating Other with id = {}", id);
        int q=Integer.parseInt(putBody.getQuantity());
        Others_dto othersDto = otherService.updateOther(id,q);
        if (othersDto == null) {
            log.error("OthersCont: Failed to update Other with id = {}", id);
            return ResponseEntity.notFound().build();
        }
        log.info("OthersCont: After update = {}", othersDto);
        return ResponseEntity.ok().body(othersDto);
    }

    @GetMapping("/other/{type}/filter/{size}")
    public ResponseEntity<?> getByTypeSize(@PathVariable String type,@PathVariable int size) {
        log.info("OthersCont: Received a request for retrieving Others based on type and size filters, type = {}, size = {}", type, size);
        List<Others_dto> othersDtos = otherService.getOtherByTypeandSize(type,size);
        log.info("OthersCont: Retrieved = {}", othersDtos);
        return ResponseEntity.ok().body(othersDtos);
    }
    @GetMapping("/other/{type}/colour/{colour}")
    public ResponseEntity<?> getByTypeColour(@PathVariable String type,@PathVariable String colour) {
        log.info("OthersCont: Received a request for retrieving Others based on type and size filters, type = {}, colour = {}", type, colour);
        List<Others_dto> othersDtos = otherService.getOtherByTypeandColour(type,colour);
        log.info("OthersCont: Retrieved = {}", othersDtos);
        return ResponseEntity.ok().body(othersDtos);
    }
    @GetMapping("/other/{type}/filter/{size}/colour/{colour}")
    public ResponseEntity<?> getByTypeSizeColour(@PathVariable String type,@PathVariable int size,@PathVariable String colour) {
        log.info("OthersCont: Received a request for retrieving Others based on type and size filters, type = {}, colour = {}, size = {}", type, colour, size);
        List<Others_dto> othersDtos = otherService.getOtherByTypeandSizeandColour(type,size,colour);
        log.info("OthersCont: Retrieved = {}", othersDtos);
        return ResponseEntity.ok().body(othersDtos);
    }
    @GetMapping("/others/search/{name}")
    public ResponseEntity<?> search(@PathVariable String name){
        log.info("OthersCont: Received a request to retrieve Others based on name filter, nane = {}", name);
        List<Others_dto> othersDtos=otherService.getOthersByName(name);
        log.info("OthersCont: Retrieved = {}", othersDtos);
        return ResponseEntity.ok().body(othersDtos);
    }

    @DeleteMapping("/others/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        log.info("OthersCont: Received a request to delete Other with id = {}", id);
        otherService.deleteById(id);
        Map<String,String> deletemap=new HashMap<>();
        deletemap.put("message","deleted successfully : "+id);
        log.info("OthersCont: Sending JSON response = {}", deletemap);
        return ResponseEntity.ok().body(deletemap);
    }

}
