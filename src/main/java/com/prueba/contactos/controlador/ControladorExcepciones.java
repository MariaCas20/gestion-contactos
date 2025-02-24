package com.prueba.contactos.controlador;

import com.prueba.contactos.modelos.DtoRespuestaError;
import com.prueba.contactos.utilidades.ExcepcionContactos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class ControladorExcepciones {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarExcepcionesGenerales(Exception ex) {
        return new ResponseEntity<>("Error interno: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExcepcionContactos.class)
    public ResponseEntity<DtoRespuestaError> manejarContactoNoEncontrado(ExcepcionContactos ex) {
        DtoRespuestaError error = new DtoRespuestaError(ex.getMensaje(), ex.getDetalle(), ex.getFecha());
        if (error.getDetalle().equals("El contacto ingresado ya existe")){
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DtoRespuestaError> manejaErroresParaValidaciones(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        BindingResult bindingResult = ex.getBindingResult();

        for (ObjectError error : bindingResult.getAllErrors()) {
            String message = error.getDefaultMessage();
            errors.add(message);
        }

        DtoRespuestaError dtoRespuestaError = new DtoRespuestaError();
        dtoRespuestaError.setDetalle(errors.get(0));
        dtoRespuestaError.setFecha(LocalDateTime.now().toString());
        dtoRespuestaError.setMensaje("Datos de entrada incorrectos");

        return new ResponseEntity<>(dtoRespuestaError, HttpStatus.BAD_REQUEST);
    }
}
