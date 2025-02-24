package com.prueba.contactos.utilidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ExcepcionContactos extends RuntimeException{

    private String mensaje;
    private String detalle;
    private String fecha;

}
