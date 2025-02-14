package com.example.viaggiAziendali.payload;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class DipendenteDTO {

    @Size(min=3,max = 15,message = "username deve essere compreso tra 3 a 15 caratteri")
    private String username;
    @Size(min = 3, message = "Nome troppo corto")
    @Size(max = 20, message = "Nome troppo lungo")
    @NotNull(message = "il nome è campo obbligatorio")
    @NotBlank(message = "il nome non puo essere in bianco")
    private String nome;
    @Size(min = 3, message = "Cognome troppo corto")
    @Size(max = 25, message = "Cognome troppo lungo")
    @NotNull(message = "il Cognome è campo obbligatorio")
    @NotBlank(message = "il Cognome non puo essere in bianco")
    private String cognome;

    @Email(message = "email non valida")
    private String email;



}
