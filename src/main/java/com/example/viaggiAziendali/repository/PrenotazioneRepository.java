package com.example.viaggiAziendali.repository;

import com.example.viaggiAziendali.entity.Dipendente;
import com.example.viaggiAziendali.entity.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione,Long> {

    boolean existsByDipendenteAndData(Dipendente dipendente, LocalDate data);
}
