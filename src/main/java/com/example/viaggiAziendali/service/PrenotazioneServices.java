package com.example.viaggiAziendali.service;


import com.example.viaggiAziendali.entity.Dipendente;
import com.example.viaggiAziendali.entity.Prenotazione;
import com.example.viaggiAziendali.entity.Viaggio;
import com.example.viaggiAziendali.payload.PrenotazioneDTO;
import com.example.viaggiAziendali.repository.DipendenteRepository;
import com.example.viaggiAziendali.repository.PrenotazioneRepository;
import com.example.viaggiAziendali.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneServices {

    @Autowired
    PrenotazioneRepository prenotazioneDAO;
    @Autowired
    ViaggioRepository viaggioDAO;
    @Autowired
    DipendenteRepository dipendenteDAO;



public  String insertPrenotazione(Long idViaggio,Long idDipen, PrenotazioneDTO prenotazioneDTO){

    Viaggio viaggio=viaggioDAO.getById(idViaggio);
    Dipendente dipendente=dipendenteDAO.getById(idDipen);

    if(prenotazioneDAO.existsByDipendenteAndData(dipendente,prenotazioneDTO.getData())){
        throw new IllegalStateException("Il dipendente ha gia una prenotazione in corso per questa data");
    }
    Prenotazione prenotazione=dto_entity(prenotazioneDTO);
    prenotazioneDAO.save(prenotazione);

    return "prenotazione effettuata";

}



    //metodi dto->entity e entity->dto


    public Prenotazione dto_entity(PrenotazioneDTO prenDTO){
        if(prenDTO!=null){
            Prenotazione prenotazione=new Prenotazione();
            prenotazione.setViaggio(viaggioDAO.getById(prenDTO.getFk_viaggio()));
            prenotazione.setDipendente(dipendenteDAO.getById(prenDTO.getFk_dipendente()));
            prenotazione.setData(prenDTO.getData());
            return prenotazione;
        }
        throw new RuntimeException("Prenotazione non identifcata");
    }

    public PrenotazioneDTO entity_dto(Prenotazione pren){

        if(pren!=null){
            PrenotazioneDTO prenotazioneDTO=new PrenotazioneDTO();
            prenotazioneDTO.setData(pren.getData());
            prenotazioneDTO.setPreferenze(pren.getPreferenze());
            prenotazioneDTO.setFk_dipendente(pren.getDipendente().getIdDipendente());
            prenotazioneDTO.setFk_viaggio(pren.getViaggio().getIdViaggio());
            return prenotazioneDTO;
        }

        throw new RuntimeException("Prenotazione non identifcata");
    }


}
