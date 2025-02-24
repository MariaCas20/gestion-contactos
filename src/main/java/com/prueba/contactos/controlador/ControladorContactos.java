package com.prueba.contactos.controlador;

import com.prueba.contactos.Servicio.Servicio;
import com.prueba.contactos.modelos.Contactos;
import com.prueba.contactos.modelos.DtoRespuesta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contactos")
public class ControladorContactos {

    @Autowired
    Servicio servicioContactos;

    // Recurso para mostrar todos los contactos existentes
    @GetMapping("/listar")
    public ResponseEntity<List<Contactos>> listarContactos() {
        List<Contactos> contactos = servicioContactos.listarContactos();
        return ResponseEntity.ok(contactos);
    }

    // Crea un nuevo contacto y devuelve una respuesta con estado 201 (Created).

    @PostMapping
    public ResponseEntity<DtoRespuesta> crearContacto(@Valid @RequestBody Contactos contacto) {
        DtoRespuesta respuesta = servicioContactos.crearContacto(contacto);
        return ResponseEntity.status(201).body(respuesta);
    }

//Actualiza un contacto existente y devuelve la respuesta con estado 200 (OK).

    @PutMapping()
    public ResponseEntity<DtoRespuesta> actualizarContacto(@Valid @RequestBody Contactos contacto) {
        DtoRespuesta respuesta = servicioContactos.actualizarContacto(contacto);
        return ResponseEntity.ok(respuesta);
    }
    //Elimina un contacto y devuelve una respuesta sin contenido (204 No Content), incluyendo encabezados personalizados con detalles de la eliminación.

    @DeleteMapping()
    public ResponseEntity<Void> eliminarContacto(@Valid @RequestBody Contactos contacto) {

        DtoRespuesta respuesta = servicioContactos.eliminarContacto(contacto);
        return ResponseEntity.noContent()
                .header("x-mensaje", respuesta.getMensaje())
                .header("x-fecha", respuesta.getFecha())
                .build();
    }

    //Busca un contacto por mobre, correo
    @GetMapping("/buscar")
    public ResponseEntity<Contactos> buscarContacto(
            @RequestParam(value = "nombre", required = false, defaultValue = "") String nombre,
            @RequestParam(value = "correo", required = false, defaultValue = "") String correo) {

        Optional<Contactos> contacto = servicioContactos.buscarContactoPorNombreOCorreo(nombre, correo);
        return ResponseEntity.ok(contacto.get());
    }
}

