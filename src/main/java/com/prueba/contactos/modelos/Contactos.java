package com.prueba.contactos.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contactos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El nombre es obligatorio")
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String fotografia;
    @Embedded
    private Direccion direccion;

}
