package com.prueba.contactos.Servicio;

import com.prueba.contactos.modelos.Contactos;
import com.prueba.contactos.modelos.DtoRespuesta;
import com.prueba.contactos.repositorio.RepositorioContacto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class Servicio {

    private final RepositorioContacto repositorioContacto;

    @Autowired
    public Servicio(RepositorioContacto repositorioContacto) {
        this.repositorioContacto = repositorioContacto;
    }


    public DtoRespuesta crearContacto(Contactos contacto) {
        repositorioContacto.save(contacto);
        DtoRespuesta respuesta = new DtoRespuesta("Contacto creado con éxito",
                LocalDateTime.now().toString());
        return respuesta;
    }

    public List<Contactos> listarContactos() {
        return repositorioContacto.findAll();
    }

    public DtoRespuesta actualizarContacto(Contactos contacto) {
        Long id = obtenerIdPorNombreApellido(contacto.getNombre(), contacto.getApellido());
        if (!repositorioContacto.existsById(id)) {
            throw new RuntimeException();
        }
        contacto.setId(id);
        repositorioContacto.save(contacto);
        DtoRespuesta respuesta = new DtoRespuesta("Contacto actualizado con éxito",
                LocalDateTime.now().toString());
        return respuesta;
    }

    public DtoRespuesta eliminarContacto(Contactos contacto) {
        Long id = obtenerIdPorNombreApellido(contacto.getNombre(), contacto.getApellido());
        if (!repositorioContacto.existsById(id)) {
            throw new RuntimeException();
        }
        repositorioContacto.deleteById(id);
        DtoRespuesta respuesta = new DtoRespuesta("Contacto eliminado con éxito",
                LocalDateTime.now().toString());
        return respuesta;
    }

    public Long obtenerIdPorNombreApellido(String nombre, String apellido) {
        Optional<Contactos> contacto = repositorioContacto.findByNombreAndApellido(nombre, apellido);
        if (contacto.isPresent()) {
            return contacto.get().getId();
        } else {

            throw new EntityNotFoundException();
        }
    }

    public Optional<Contactos> buscarContactoPorNombreOCorreo(String nombre, String correo) {
        Optional<Contactos> contacto= repositorioContacto.findByNombreContainingAndCorreoContaining(nombre, correo);
        if (contacto.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return contacto;
    }
}
