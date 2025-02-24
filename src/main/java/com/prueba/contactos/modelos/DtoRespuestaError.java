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

    private String mensaje;
    private String detalle;
    private String fecha;
}
