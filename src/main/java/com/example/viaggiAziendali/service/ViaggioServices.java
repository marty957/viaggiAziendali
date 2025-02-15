package com.example.viaggiAziendali.service;


import com.example.viaggiAziendali.entity.Viaggio;
import com.example.viaggiAziendali.exception.NotFoundExcep;
import com.example.viaggiAziendali.payload.ViaggioDTO;
import com.example.viaggiAziendali.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViaggioServices {

    @Autowired
    ViaggioRepository viaggioDAO;

    //inserimento :
    public Viaggio insertViaggi(ViaggioDTO viaggioDTO){
        Viaggio viaggio=dto_entity(viaggioDTO);
        viaggioDAO.save(viaggio);
        return viaggio;
    }

    public String popolaViaggi(List<ViaggioDTO> lista){
        for(ViaggioDTO viaggioDTO:lista){
            Viaggio viaggio=dto_entity(viaggioDTO);
            viaggioDAO.save(viaggio);
        }
        return "la lista viaggi è stata inserita";
    }

    //modifica:

    public Viaggio updateViaggio(long id, ViaggioDTO viaggioDTO){
        Viaggio viaggioModificato= viaggioDAO.getById(id);
        if(viaggioModificato==null){
            throw new NotFoundExcep("Viaggio non trovato");
        }

        viaggioModificato.setDataViaggio(viaggioDTO.getDataViaggio());
        viaggioModificato.setDestinazione(viaggioDTO.getDestinazione());
        viaggioModificato.setStato(viaggioDTO.getStato());
        viaggioDAO.save(viaggioModificato);
        return viaggioModificato;
    }

    public Viaggio updateStatoViaggio(long id, String stato){
        Viaggio viaggio= viaggioDAO.getById(id);
        if(viaggio==null){
            throw new NotFoundExcep("Viaggio non trovato");
        }

        viaggio.setStato(stato);
        viaggioDAO.save(viaggio);
        return viaggio;
    }
//ricerca:

    public Viaggio getById(long id){
        return viaggioDAO.findById(id).orElseThrow(()->new NotFoundExcep(id));
    }


    public List<Viaggio> getAllViaggi(){
        return viaggioDAO.findAll();
    }
     public List<ViaggioDTO> getAllViaggiPaginazione(){

        List<Viaggio> listaViaggi= viaggioDAO.findAll();
        List<ViaggioDTO> listaViaggiDto=new ArrayList<>();

        for(Viaggio viaggio:listaViaggi){
            ViaggioDTO viaggioDTO=entity_dto(viaggio);
            listaViaggiDto.add(viaggioDTO);
        }

        return listaViaggiDto;


}
//cancellazione

    public String deleteViaggio(Long id){
        viaggioDAO.deleteById(id);
        return "viaggio cancellato";

    }

    //metodi dto->entity e entity->dto

    public Viaggio dto_entity(ViaggioDTO viaggioDTO){
        Viaggio viaggio=new Viaggio();

        viaggio.setDestinazione(viaggioDTO.getDestinazione());
        viaggio.setDataViaggio(viaggioDTO.getDataViaggio());
        viaggio.setStato(viaggioDTO.getStato());

        return viaggio;
    }


    public ViaggioDTO entity_dto(Viaggio viaggio){
        ViaggioDTO viaggioDTO=new ViaggioDTO();
        viaggioDTO.setDataViaggio(viaggio.getDataViaggio());
        viaggioDTO.setStato(viaggio.getStato());
        viaggioDTO.setDestinazione(viaggio.getDestinazione());

        return viaggioDTO;
    }

}
