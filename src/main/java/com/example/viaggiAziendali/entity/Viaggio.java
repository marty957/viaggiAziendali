package com.example.viaggiAziendali.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "viaggi")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Viaggio {

    @Id
    @GeneratedValue
    private long idViaggio;

    @Column(nullable = false)
    private String destinazione;

    private LocalDate dataViaggio;
    private String stato;
    @OneToMany(mappedBy = "viaggio")
    private List<Prenotazione> prenotazioni;



}
