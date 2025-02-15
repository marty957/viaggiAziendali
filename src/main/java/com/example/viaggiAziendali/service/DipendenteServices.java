package com.example.viaggiAziendali.service;


import com.example.viaggiAziendali.entity.Dipendente;
import com.example.viaggiAziendali.exception.NotFoundExcep;
import com.example.viaggiAziendali.payload.DipendenteDTO;
import com.example.viaggiAziendali.repository.DipendenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DipendenteServices {

    @Autowired
    DipendenteRepository dipendenteDAO;

//inserimento dipendenti da parte del client

    public Dipendente insertDipendenti(DipendenteDTO dipendenteDTO){

        Dipendente nuovoDipendente=dto_entity(dipendenteDTO);
        dipendenteDAO.save(nuovoDipendente);
        return  nuovoDipendente;
    }

    public String popolaDipendenti(List<DipendenteDTO> lista){
        for(DipendenteDTO dipendenteDTO:lista){
            Dipendente dipendente=dto_entity(dipendenteDTO);
            dipendenteDAO.save(dipendente);
        }
        return "lista dipendenti inserita";
    }

//RICERCA

    public Dipendente getByID(Long id){
        Optional<Dipendente> dip= dipendenteDAO.findById(id);
        if(dip.isPresent()){
            return dip.get();
        }else {
            throw new EntityNotFoundException("Dipendete non trovato");
        }


    }

    public List<DipendenteDTO> getAllDipendentiPaginazione(){
        List<Dipendente> listaDipendenti=dipendenteDAO.findAll();
        List<DipendenteDTO> listaDipendetiDTO= new ArrayList<>();

        for(Dipendente sinoloDipendete: listaDipendenti){
            DipendenteDTO dipendenteDTO=entity_dto(sinoloDipendete);
            listaDipendetiDTO.add(dipendenteDTO);
        }
        return listaDipendetiDTO;
    }


    public List<Dipendente> getAllDipendenti(){
        return dipendenteDAO.findAll();
    }

    //Modifica

    public Dipendente modificaDipendene(long id, DipendenteDTO dip){
        Dipendente dipendenteModificato= dipendenteDAO.getById(id);

         if(dipendenteModificato==null){
             throw new NotFoundExcep("Dipendente non trovato");
         }

        dipendenteModificato.setEmail(dip.getEmail());
        dipendenteModificato.setNome(dip.getNome());
        dipendenteModificato.setUsername(dip.getUsername());
        dipendenteModificato.setCognome(dip.getCognome());
        dipendenteModificato.setFoto(dip.getFoto());
        dipendenteDAO.save(dipendenteModificato);
        return dipendenteModificato;
    }

//CANCELLAZIONE

    public String cancellaDipendente(Long id){
        dipendenteDAO.deleteById(id);
        return "dipendente cancellato";
    }
    //metodi dto->entit{y e entity->dto

    public Dipendente dto_entity(DipendenteDTO dipDTO){
        if(dipDTO!=null){
            Dipendente dipendente=new Dipendente();
            dipendente.setUsername(dipDTO.getUsername());
            dipendente.setNome(dipDTO.getNome());
            dipendente.setCognome(dipDTO.getCognome());
            dipendente.setEmail(dipDTO.getEmail());
            return dipendente;
        }
        throw new RuntimeException("Dipendete non identificato");
    }

    public DipendenteDTO entity_dto(Dipendente dip){
        if(dip!=null){
            DipendenteDTO dipendenteDTO=new DipendenteDTO();
            dipendenteDTO.setUsername(dip.getNome());
            dipendenteDTO.setCognome(dip.getCognome());
            dipendenteDTO.setUsername(dip.getUsername());
            dipendenteDTO.setEmail(dip.getEmail());
            return dipendenteDTO;
        }
        throw new RuntimeException("Dipendete non identificato");
    }

}
