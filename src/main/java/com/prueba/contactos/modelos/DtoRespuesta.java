package com.prueba.contactos.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoRespuesta {

    private String mensaje; // Almacena el mensaje de respuesta (por ejemplo, un mensaje de éxito o error).
    private String fecha; // Almacena la fecha y hora de la respuesta.

}
