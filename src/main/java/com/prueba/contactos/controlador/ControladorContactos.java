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

    @GetMapping("/listar")
    public ResponseEntity<List<Contactos>> listarContactos() {
        List<Contactos> contactos = servicioContactos.listarContactos();
        return ResponseEntity.ok(contactos);
    }

        @PostMapping
    public ResponseEntity<DtoRespuesta> crearContacto(@Valid @RequestBody Contactos contacto) {
        DtoRespuesta respuesta = servicioContactos.crearContacto(contacto);
        return ResponseEntity.status(201).body(respuesta);
    }

    @PutMapping()
    public ResponseEntity<DtoRespuesta> actualizarContacto(@Valid @RequestBody Contactos contacto) {
        DtoRespuesta respuesta = servicioContactos.actualizarContacto(contacto);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping()
    public ResponseEntity<Void> eliminarContacto(@Valid @RequestBody Contactos contacto) {

        DtoRespuesta respuesta = servicioContactos.eliminarContacto(contacto);
        return ResponseEntity.noContent()
                .header("x-mensaje", respuesta.getMensaje())
                .header("x-fecha", respuesta.getFecha())
                .build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Contactos> buscarContacto(
            @RequestParam(value = "nombre", required = false, defaultValue = "") String nombre,
            @RequestParam(value = "correo", required = false, defaultValue = "") String correo) {

        Optional<Contactos> contacto = servicioContactos.buscarContactoPorNombreOCorreo(nombre, correo);
        return ResponseEntity.ok(contacto.get());
    }
}

