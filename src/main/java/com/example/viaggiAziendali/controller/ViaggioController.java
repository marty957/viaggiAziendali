package com.example.viaggiAziendali.controller;


import com.example.viaggiAziendali.entity.Viaggio;
import com.example.viaggiAziendali.exception.NotFoundExcep;
import com.example.viaggiAziendali.payload.PrenotazioneDTO;
import com.example.viaggiAziendali.payload.ViaggioDTO;
import com.example.viaggiAziendali.service.ViaggioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    // 4 PUT - http://localhost:3001/viaggi/{id}
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio modificaViaggio(@PathVariable long id,@RequestBody @Validated ViaggioDTO viaggioDTO){
        return service.updateViaggio(id,viaggioDTO);
    }

    // 5 PATCH - http://localhost:3001/viaggi/{id}

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Viaggio> modificaStatoViaggio(@PathVariable long id,
                                                           @Validated @RequestBody String statoViaggioDTO,
                                                           BindingResult validazione) {

        if(validazione.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        try{
            Viaggio v= service.updateStatoViaggio(id,statoViaggioDTO);
            return ResponseEntity.ok(v);
        }catch (NotFoundExcep e){
            return ResponseEntity.notFound().build();
        }

    }


    // PRENOTAZIONE:
    @PatchMapping("/{id}/assegna")
    public ResponseEntity<?> prenotazione(@PathVariable long id, @RequestBody @Validated PrenotazioneDTO body,
                                          BindingResult validazione){
        if(validazione.hasErrors()){
           return ResponseEntity.badRequest().build();
        }

        try{
            service.asssegnaDip(id, body.getFk_dipendente());
            return ResponseEntity.ok().build();
        }catch (NotFoundExcep excep){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(excep.getMessage());

        }
    }



    // 6 DELETE - http://localhost:3001/viaggi/{id}
    @DeleteMapping("/{id}")
    public void deleteViaggio(@PathVariable Long id){
        service.deleteViaggio(id);
    }


}
