package com.example.viaggiAziendali.payload;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ViaggioDTO {


    @NotNull(message = "la destinazione è un campo obbligatorio")
    private String destinazione;
    @NotNull(message = "data obbligatoria")
    private LocalDate dataViaggio;
    @Size(min=6,max=20,message = "campo compreso tra 6 a 20 caratteri massimo")
    @NotNull(message = "campo obbligatorio")
    @NotEmpty(message = "non può essere lasciato in bianco")
    private String stato;



}
