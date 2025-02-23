package com.prueba.contactos.repositorio;

import com.prueba.contactos.modelos.Contactos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioContacto extends JpaRepository<Contactos, Long> {

}
