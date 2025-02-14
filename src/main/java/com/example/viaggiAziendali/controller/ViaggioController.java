package com.example.viaggiAziendali.controller;


import com.example.viaggiAziendali.entity.Viaggio;
import com.example.viaggiAziendali.payload.ViaggioDTO;
import com.example.viaggiAziendali.service.ViaggioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    ViaggioServices service;



    // 1 - POST http://localhost:3001/viaggi/nuovoViaggio

    @PostMapping("/nuovoViaggio")
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio creazioneViaggio(@RequestBody @Validated ViaggioDTO body){

        return service.insertViaggi(body);
    }

    // 2 GET - http://localhost:3001/viaggi

    @GetMapping
    public List<Viaggio> getAllViaggi(){
        return service.getAllViaggi();
    }


    // 3 GET - http://localhost:3001/viaggi/{id}

    @GetMapping("/{id}")
    public Viaggio getViaggioById(@PathVariable Long id){
        return service.getById(id);
    }

    // 4 PUT - http://localhost:3001/viaggi
    // 5 PATCH - http://localhost:3001/viaggi



    // 6 DELETE - http://localhost:3001/viaggi/{id}
    @DeleteMapping("/{id]")
    public void deleteViaggio(@PathVariable Long id){
        service.deleteViaggio(id);
    }


}
