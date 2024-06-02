package com.example.ketanStores.cont;


import com.example.ketanStores.dto.ChudidarDto;
import com.example.ketanStores.dto.PutBody;
import com.example.ketanStores.enums.ChudidarEnum;
import com.example.ketanStores.service.ChudidarService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ChudidarCont {

    private final ChudidarService chudidarService;

    public ChudidarCont(ChudidarService chudidarService) {
        this.chudidarService = chudidarService;
    }

    @PostMapping("/chudidar")
    public ResponseEntity<?> postChuridar(@RequestParam("name") String name, @RequestParam("img") MultipartFile file, @RequestParam("price") int price, @RequestParam("quantity") int quantity, @RequestParam("type") String chudidar,@RequestParam("size") int size) throws IOException, SQLException {
        log.info("ChudidarCont: Received request to create Chudidar with parameters : name = {}, price = {}, quantity = {}, type = {}, size = {}", name, price, quantity, chudidar, size);
        byte[] bytes = file.getBytes();
        Blob blob = new SerialBlob(bytes);
        ChudidarEnum chudidarEnum=ChudidarEnum.valueOf(chudidar);
        ChudidarDto chudidarDto=chudidarService.createChuridar(name,price,quantity,chudidarEnum,blob,size);
        if(chudidarDto==null){
            log.error("ChudidarCont: Failed to create Chudidar with parameters: name = {}, price = {}, quantity = {}, type = {}, size = {}", name, price, quantity, chudidar, size);
            return ResponseEntity.badRequest().build();
        }
        log.info("ChudidarCont: Chudidar created successfully: {}", chudidarDto);
        return ResponseEntity.ok().body(chudidarDto);
    }
    @GetMapping("/churidar/{id}")
    public ResponseEntity<?> getChuridar(@PathVariable Long id){
        log.info("ChudidarCont: Received a request to retrieve Chudidar with id = {}", id);
        ChudidarDto chudidarDto = chudidarService.getChudidar(id);
        if (chudidarDto==null) {
            log.error("ChudidarCont: Failed to request retrieve Chudidar with id = {}", id);
            return ResponseEntity.notFound().build();
        }
        log.info("ChudidarCont: Successfully retrieved Chudidar = {}", chudidarDto);
        return ResponseEntity.ok().body(chudidarDto);
    }
    @GetMapping("/churidars")
    public ResponseEntity<?> getChuridars(){
        List<ChudidarDto> chudidarDtos=chudidarService.getChuridars();
        log.info("ChudidarCont: Received a request for retrieving all Chudidars. Got = {}", chudidarDtos);
        return ResponseEntity.ok().body(chudidarDtos);
    }
    @GetMapping("/chudidar/{type}")
    public ResponseEntity<?> getByType(@PathVariable String type){
        log.info("ChudidarCont: Request to retrieve Chudidars of type = {}", type);
        List<ChudidarDto> chudidarDtos=chudidarService.getChudidarByType(type);
        log.info("ChudidarCont: Retrieved = {}", chudidarDtos);
        return ResponseEntity.ok().body(chudidarDtos);
    }
    @PutMapping("/chudidar/update/{id}")
    public ResponseEntity<?> updateChudidar(@PathVariable Long id, @RequestBody PutBody putBody) {
        log.info("ChudidarCont: Received a request for updating Chudidar with id = {}", id);
        int q=Integer.parseInt(putBody.getQuantity());
        ChudidarDto chudidarDto = chudidarService.updateChudidar(id,q);
        if (chudidarDto == null) {
            log.error("ChudidarCont: Chudidar not found.");
            return ResponseEntity.notFound().build();
        }
        log.info("ChudidarCont: Updated Chudidar = {}", chudidarDto);
        return ResponseEntity.ok().body(chudidarDto);
    }
    @GetMapping("/chudidar/{type}/filter/{size}")
    public ResponseEntity<?> getByTypeSize(@PathVariable String type,@PathVariable int size) {
        log.info("ChudidarCont: Received a request for retrieving Chudidars filtered wrt type and size, type = {}, size = {}", type, size);
        List<ChudidarDto> chudidarDtos = chudidarService.getChudidarByTypeandSize(type,size);
        log.info("ChudidarCont: Received Chudidars = {}", chudidarDtos);
        return ResponseEntity.ok().body(chudidarDtos);
    }
    @DeleteMapping("/chudidar/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        log.info("ChudidarCont: Received a request for deleting Chudidar with id = {}", id);
        chudidarService.deleteById(id);
        Map<String,String> deletemap=new HashMap<>();
        deletemap.put("message","deleted successfully"+id);
        log.info("ChudidarCont: Returning JSON response = {}", deletemap);
        return ResponseEntity.ok().body(deletemap);

    }
    @GetMapping("/chudidar/search/{name}")
    public ResponseEntity<?> search(@PathVariable String name){
        log.info("ChudidarCont: Received a request to retrieve Chudidars with name = {}", name);
        List<ChudidarDto> chudidarDtos=chudidarService.getChudidarByName(name);
        log.info("ChudidarCont: Retrieved Chudidars = {}", chudidarDtos);
        return ResponseEntity.ok().body(chudidarDtos);
    }
}
