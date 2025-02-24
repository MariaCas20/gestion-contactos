package com.prueba.contactos.repositorio;

import com.prueba.contactos.modelos.Contactos;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RepositorioContacto  extends JpaRepository<Contactos, Long>{

    // Busca un contacto por nombre y apellido.
    // Retorna un Optional con el contacto encontrado, o vacío si no se encuentra.
    Optional<Contactos> findByNombreAndApellido(String nombre, String apellido);


    // Busca un contacto que contenga el nombre y el correo proporcionado (búsqueda parcial).
    // Retorna un Optional con el contacto encontrado, o vacío si no se encuentra.
    Optional<Contactos> findByNombreContainingAndCorreoContaining(String nombre, String correo);
}
