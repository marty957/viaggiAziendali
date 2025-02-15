package com.example.viaggiAziendali.payload;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioneDTO {

    @NotNull(message = "specificare il viaggio")
    private Long fk_viaggio;
    @NotNull(message = "specificare il dipendente")
    private Long fk_dipendente;
    @NotNull(message = "data obbligatoria")
    private LocalDate data;
    @Size(min = 10,message = "campi minimi 10")
    @Size(max = 150,message ="campi max 150" )
    private String preferenze;

}
