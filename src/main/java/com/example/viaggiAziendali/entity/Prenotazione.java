package com.example.viaggiAziendali.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prenotazione {

    @Id
    @GeneratedValue
    private long idPrenotazione;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    @Column(nullable = false)
    private LocalDate data;
    private String preferenze;



}
