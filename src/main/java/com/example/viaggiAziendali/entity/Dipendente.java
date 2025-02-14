package com.example.viaggiAziendali.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dipendenti")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dipendente {

    @Id
    @GeneratedValue
    private long idDipendente;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(unique = true,nullable = false)
    private String email;

    @OneToMany(mappedBy = "dipendente")
    List<Prenotazione> listaPrenotazione;
}
