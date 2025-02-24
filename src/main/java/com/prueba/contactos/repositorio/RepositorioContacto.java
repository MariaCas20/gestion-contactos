package com.prueba.contactos.repositorio;

import com.prueba.contactos.modelos.Contactos;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RepositorioContacto  extends JpaRepository<Contactos, Long>{

    Optional<Contactos> findByNombreAndApellido(String nombre, String apellido);
    Optional<Contactos> findByNombreContainingAndCorreoContaining(String nombre, String correo);
}
