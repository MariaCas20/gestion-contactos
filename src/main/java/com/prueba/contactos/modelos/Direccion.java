package com.prueba.contactos.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {

    private String pais;

    private String ciudad;

    private String estado;

    private String calle;

    private String codigoPostal;

    private String numero;
}
