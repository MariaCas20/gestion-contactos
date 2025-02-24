package com.prueba.contactos.Servicio;

import com.prueba.contactos.modelos.Contactos;
import com.prueba.contactos.modelos.DtoRespuesta;
import com.prueba.contactos.repositorio.RepositorioContacto;
import com.prueba.contactos.utilidades.ExcepcionContactos;
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
        vaidarSiExiste(contacto.getNombre(), contacto.getApellido());
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
        contacto.setId(id);
        repositorioContacto.save(contacto);
        DtoRespuesta respuesta = new DtoRespuesta("Contacto actualizado con éxito",
                LocalDateTime.now().toString());
        return respuesta;
    }

    public DtoRespuesta eliminarContacto(Contactos contacto) {
        Long id = obtenerIdPorNombreApellido(contacto.getNombre(), contacto.getApellido());
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
            throw new ExcepcionContactos("Datos de entrada incorrectos",
                    "No existe un contacto para el nombre y apellido ingresado.",
                    LocalDateTime.now().toString());
        }

    }

    public Optional<Contactos> buscarContactoPorNombreOCorreo(String nombre, String correo) {
        Optional<Contactos> contacto= repositorioContacto.findByNombreContainingAndCorreoContaining(nombre, correo);
        if (contacto.isEmpty()) {
            throw new ExcepcionContactos("Datos de entrada incorrectos",
                    "No existe un contacto para el nombre o correo ingresado",
                    LocalDateTime.now().toString());
        }
        return contacto;
    }

    public void vaidarSiExiste(String nombre, String apellido){
        Optional<Contactos> contacto = repositorioContacto.findByNombreAndApellido(nombre, apellido);
        if (!contacto.isEmpty()) {
            throw new ExcepcionContactos("Datos de entrada incorrectos",
                    "El contacto ingresado ya existe",
                    LocalDateTime.now().toString());
        }
    }

}
