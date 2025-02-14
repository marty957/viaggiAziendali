package com.example.viaggiAziendali.controller;


import com.example.viaggiAziendali.entity.Dipendente;
import com.example.viaggiAziendali.payload.DipendenteDTO;
import com.example.viaggiAziendali.service.DipendenteServices;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    DipendenteServices service;

    // 1. - POST http://localhost:3001/dipendenti/nuovoPost
    @PostMapping("/nuovoPost")
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipendente(@RequestBody @Validated DipendenteDTO body) {
        return service.insertDipendenti(body);
    }

    // 2. GET http://localhost:3001/dipendenti
    @GetMapping
    public List<Dipendente> riceraDipendenti() {
        return service.getAllDipendenti();
    }

    // 3 GET SINGOLO - http://localhost:3001/dipendenti/{id}

    @GetMapping("/{id}")
    public Dipendente getOneDipend(@PathVariable long id){
        return service.getByID(id);
    }

    // 4 PUT - http://localhost:3001/dipendenti/

    // 5 PATCH - http://localhost:3001/dipendenti
    // 6  DELETE http://localhost:3001/dipendenti/{id}

    @DeleteMapping("/{id}")
    public void deleteDipendete(@PathVariable long id){
        service.cancellaDipendente(id);
    }






}
