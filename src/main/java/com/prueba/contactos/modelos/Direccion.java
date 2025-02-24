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

    private String pais;  // Almacena el nombre del país de la dirección.

    private String ciudad; // Almacena el nombre de la ciudad de la dirección.

    private String estado; // Almacena el nombre del estado o provincia de la dirección.

    private String calle; // Almacena el nombre de la calle de la dirección.

    private String codigoPostal; // Almacena el código postal de la dirección.

    private String numero; // Almacena el número de la casa o apartamento en la dirección.
}
