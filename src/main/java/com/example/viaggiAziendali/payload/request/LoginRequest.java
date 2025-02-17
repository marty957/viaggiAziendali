package com.example.viaggiAziendali.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message="Il campo username è obbligatorio")
    private String username;

    @NotBlank(message="Il campo password è obbligatorio")
    private String password;
}
