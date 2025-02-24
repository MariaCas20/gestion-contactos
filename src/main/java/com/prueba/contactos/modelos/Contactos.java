package com.prueba.contactos.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    // Marca el campo como la clave primaria de la entidad.

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Genera el valor del identificador de manera automática utilizando
    // la estrategia de auto-incremento de la base de datos.

    @NotNull(message = "El nombre es obligatorio")
    // Valida que el campo nombre no sea nulo.

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    // Valida que el campo apellido no esté vacío o solo contenga espacios en blanco.

    @NotNull(message = "El apellido es obligatorio")
    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    private String telefono;

    @NotNull(message = "El correo es obligatorio")
    // Valida que el campo correo no sea nulo.

    @Email(message = "El correo debe ser válido")
    private String correo;

    private String fotografia;
    // Es un campo opcional para almacenar la URL
    // o nombre del archivo de una fotografía.


    @Embedded
    private Direccion direccion;
    // Indica que la clase Direccion está embebida dentro de esta entidad,
    // es decir, forma parte de la entidad sin tener su propia tabla.

}
