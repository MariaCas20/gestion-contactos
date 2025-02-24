package com.prueba.contactos.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DtoRespuestaError {

    private String mensaje;
    private String detalle;
    private String fecha;
}
