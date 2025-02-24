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
public class Servicio { // Constructor que inyecta el repositorio de contactos.

    private final RepositorioContacto repositorioContacto;

    @Autowired
    public Servicio(RepositorioContacto repositorioContacto) {
        this.repositorioContacto = repositorioContacto;
    }

    // metodo para crear un contacto
    public DtoRespuesta crearContacto(Contactos contacto) {
        vaidarSiExiste(contacto.getNombre(), contacto.getApellido());// Verifica si el contacto ya existe.
        repositorioContacto.save(contacto); // Guarda el contacto en la base de datos.
        DtoRespuesta respuesta = new DtoRespuesta("Contacto creado con éxito",
                LocalDateTime.now().toString());
        return respuesta; // Devuelve una respuesta con un mensaje de éxito.
    }
    //metodo para listar todos los  contactos
    public List<Contactos> listarContactos() {
        return repositorioContacto.findAll();
    }
    // metodo
    //  para actualizar
    //  un contacto existente.
    public DtoRespuesta actualizarContacto(Contactos contacto) {
        Long id = obtenerIdPorNombreApellido(contacto.getNombre(), contacto.getApellido());
        contacto.setId(id);// Establece el ID del contacto.
        repositorioContacto.save(contacto);// Guarda el contacto actualizado en la base de datos
        DtoRespuesta respuesta = new DtoRespuesta("Contacto actualizado con éxito",
                LocalDateTime.now().toString());
        return respuesta;// Devuelve una respuesta con un mensaje de éxito.
    }

    // metodo para eliminar un contacto.
    public DtoRespuesta eliminarContacto(Contactos contacto) {
        Long id = obtenerIdPorNombreApellido(contacto.getNombre(), contacto.getApellido());
        repositorioContacto.deleteById(id);  // Elimina el contacto de la base de datos.
        DtoRespuesta respuesta = new DtoRespuesta("Contacto eliminado con éxito",
                LocalDateTime.now().toString());
        return respuesta; // Devuelve una respuesta con un mensaje de éxito.
    }

    //metodo para obtener el id de un contacto por nombre y apellido.
    public Long obtenerIdPorNombreApellido(String nombre, String apellido) {
        Optional<Contactos> contacto = repositorioContacto.findByNombreAndApellido(nombre, apellido);
        if (contacto.isPresent()) {
            return contacto.get().getId(); // Si lo encuentra, devuelve el ID del contacto.
        } else {
            // Si no se encuentra, lanza una excepción con un mensaje de error.
            throw new ExcepcionContactos("Datos de entrada incorrectos",
                    "No existe un contacto para el nombre y apellido ingresado.",
                    LocalDateTime.now().toString());
        }

    }

    // metodo para buscar
    //  un contacto por nombre o correo.
    public Optional<Contactos> buscarContactoPorNombreOCorreo(String nombre, String correo) {
        Optional<Contactos> contacto= repositorioContacto.findByNombreContainingAndCorreoContaining(nombre, correo);
        if (contacto.isEmpty()) {
            // Si no se encuentra, lanza una excepción con un mensaje de error.
            throw new ExcepcionContactos("Datos de entrada incorrectos",
                    "No existe un contacto para el nombre o correo ingresado",
                    LocalDateTime.now().toString());
        }
        return contacto;// Devuelve el contacto encontrado.
    }

    //metodo para verificar si un contacto ya existe
    public void vaidarSiExiste(String nombre, String apellido){
        Optional<Contactos> contacto = repositorioContacto.findByNombreAndApellido(nombre, apellido); // Busca el contacto por nombre y apellido.
        if (!contacto.isEmpty()) {

            // Si ya existe, lanza una excepción con un mensaje de error.
            throw new ExcepcionContactos("Datos de entrada incorrectos",
                    "El contacto ingresado ya existe",
                    LocalDateTime.now().toString());
        }
    }

}
