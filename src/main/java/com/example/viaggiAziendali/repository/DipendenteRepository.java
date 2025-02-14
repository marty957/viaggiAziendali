package com.example.viaggiAziendali.repository;

import com.example.viaggiAziendali.entity.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteRepository extends JpaRepository<Dipendente,Long> {
}
