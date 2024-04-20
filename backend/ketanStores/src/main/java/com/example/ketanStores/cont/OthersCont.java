package com.example.ketanStores.cont;

import com.example.ketanStores.dto.Others_dto;
import com.example.ketanStores.dto.UserDto;
import com.example.ketanStores.service.Other_service;
import com.example.ketanStores.service.Others_serviceimp;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/others")
public class OthersCont {
    @Autowired
    Others_serviceimp othersServiceimp;

    @GetMapping("/get/{id}")
    private Others_dto getOthersById(@PathVariable Long id) {
        try {
            return othersServiceimp.getOthersById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
