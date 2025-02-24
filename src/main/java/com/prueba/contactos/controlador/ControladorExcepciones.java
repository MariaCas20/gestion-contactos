package com.prueba.contactos.controlador;

import com.prueba.contactos.modelos.DtoRespuestaError;
import com.prueba.contactos.utilidades.ExcepcionContactos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControladorExcepciones {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarExcepcionesGenerales(Exception ex) {
        return new ResponseEntity<>("Error interno: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExcepcionContactos.class)
    public ResponseEntity<DtoRespuestaError> manejarContactoNoEncontrado(ExcepcionContactos ex) {
        DtoRespuestaError error = new DtoRespuestaError(ex.getMensaje(), ex.getDetalle(), ex.getFecha());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
