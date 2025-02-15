package com.example.viaggiAziendali.controller;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    DipendenteServices service;
    @Autowired
    Cloudinary cloudinary;

    // 1. - POST http://localhost:3001/dipendenti/nuovoDipendente
    @PostMapping("/nuovoDipendente")
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipendente(@RequestBody @Validated DipendenteDTO body) {
        return service.insertDipendenti(body);
    }

    @PostMapping("/nuovoDipendenteConFoto")
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipWithPicture(@RequestPart("fotoDipendente")MultipartFile fotoDip,@RequestPart @Validated DipendenteDTO dipDTO,BindingResult validazione){
        if(validazione.hasErrors()){
            String messaggioErr = "errore validazione \n";
            for (ObjectError errore : validazione.getAllErrors()){
                messaggioErr += errore.getDefaultMessage() + " \n";
            }
        }
        try{
            Map mappa= cloudinary.uploader().upload(fotoDip.getBytes(), ObjectUtils.emptyMap());
            String urlImage = (String) mappa.get(fotoDip);
            dipDTO.setFoto(urlImage);
            Dipendente dip= service.insertDipendenti(dipDTO);
            return dip;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    // 4 PUT - http://localhost:3001/dipendenti/{id}

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente findAndUpdate(@PathVariable long id,@RequestBody @Validated DipendenteDTO dip){
        return service.modificaDipendene(id,dip);
    }


    // 5  DELETE http://localhost:3001/dipendenti/{id}

    @DeleteMapping("/{id}")
    public void deleteDipendete(@PathVariable long id){
        service.cancellaDipendente(id);
    }









}
