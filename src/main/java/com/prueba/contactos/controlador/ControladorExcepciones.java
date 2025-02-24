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

    // Maneja excepciones generales y devuelve un error 500.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarExcepcionesGenerales(Exception ex) {
        return new ResponseEntity<>("Error interno: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Maneja excepciones personalizadas de contactos y devuelve un error 400
    // o 404 según el caso.
    @ExceptionHandler(ExcepcionContactos.class)
    public ResponseEntity<DtoRespuestaError> manejarContactoNoEncontrado(ExcepcionContactos ex) {
        DtoRespuestaError error = new DtoRespuestaError(ex.getMensaje(), ex.getDetalle(), ex.getFecha());
        if (error.getDetalle().equals("El contacto ingresado ya existe")){
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    //Este metodo maneja excepciones del tipo MethodArgumentNotValidException,
// que se lanzan cuando los datos de entrada no cumplen con las
// validaciones definidas en el controlador.

    public ResponseEntity<DtoRespuestaError> manejaErroresParaValidaciones(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();

        // Obtenemos el BindingResult de la excepción para acceder a los errores de validación
        BindingResult bindingResult = ex.getBindingResult();

        // Iteramos sobre todos los errores de validación y extraemos el mensaje de cada uno.
        for (ObjectError error : bindingResult.getAllErrors()) {
            String message = error.getDefaultMessage();// Obtenemos el mensaje de error.
            errors.add(message);// Agregamos el mensaje a la lista de errores.
        }

        // Creamos una instancia de DtoRespuestaError para estructurar la respuesta de error.
        DtoRespuestaError dtoRespuestaError = new DtoRespuestaError();

        dtoRespuestaError.setDetalle(errors.get(0)); // Establecemos el primer mensaje de error como detalle.
        // (Se puede modificar para mostrar más detalles si es necesario).

        dtoRespuestaError.setFecha(LocalDateTime.now().toString());
        dtoRespuestaError.setMensaje("Datos de entrada incorrectos");

        return new ResponseEntity<>(dtoRespuestaError, HttpStatus.BAD_REQUEST);
        // devolvemos la respuesta con un estado BAD_REQUEST (400) y el DTO de error.
    }
}
