package com.example.viaggiAziendali.repository;

import com.example.viaggiAziendali.entity.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViaggioRepository extends JpaRepository<Viaggio,Long> {
}
