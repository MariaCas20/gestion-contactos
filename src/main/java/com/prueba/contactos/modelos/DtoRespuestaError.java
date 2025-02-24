package com.prueba.contactos.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DtoRespuestaError {

    private String mensaje; // Almacena el mensaje de error (por ejemplo, "Datos de entrada incorrectos").
    private String detalle;// Almacena detalles adicionales sobre el error (como el mensaje específico de validación).
    private String fecha; // Almacena la fecha y hora en que ocurrió el error
}
