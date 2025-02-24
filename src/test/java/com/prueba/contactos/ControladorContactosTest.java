package com.prueba.contactos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.contactos.Servicio.Servicio;
import com.prueba.contactos.controlador.ControladorContactos;
import com.prueba.contactos.modelos.Contactos;
import com.prueba.contactos.modelos.DtoRespuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ControladorContactosTest {

    private MockMvc mockMvc;

    @Mock
    private Servicio servicioContactos;

    @InjectMocks
    private ControladorContactos controladorContactos;

    private Contactos contacto;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controladorContactos).build();

        contacto = new Contactos(1L, "Maria", "Castillo", "1234567890", "maria@example.com", null, null);
    }

    @Test
    void testListarContactos() throws Exception {
        when(servicioContactos.listarContactos()).thenReturn(Collections.singletonList(contacto));

        mockMvc.perform(get("/api/contactos/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Maria"));

        verify(servicioContactos, times(1)).listarContactos();
    }

    @Test
    void testCrearContacto() throws Exception {
        DtoRespuesta respuesta = new DtoRespuesta("Contacto creado", "2025-02-23");
        when(servicioContactos.crearContacto(any(Contactos.class))).thenReturn(respuesta);

        mockMvc.perform(post("/api/contactos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contacto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.mensaje").value("Contacto creado"));

        verify(servicioContactos, times(1)).crearContacto(any(Contactos.class));
    }

    @Test
    void testActualizarContacto() throws Exception {
        DtoRespuesta respuesta = new DtoRespuesta("Contacto actualizado", "2025-02-23");
        when(servicioContactos.actualizarContacto(any(Contactos.class))).thenReturn(respuesta);

        mockMvc.perform(put("/api/contactos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contacto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Contacto actualizado"));

        verify(servicioContactos, times(1)).actualizarContacto(any(Contactos.class));
    }

    @Test
    void testEliminarContacto() throws Exception {
        DtoRespuesta respuesta = new DtoRespuesta("Contacto eliminado", "2025-02-23");
        when(servicioContactos.eliminarContacto(any(Contactos.class))).thenReturn(respuesta);

        mockMvc.perform(delete("/api/contactos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contacto)))
                .andExpect(status().isNoContent())
                .andExpect(header().string("x-mensaje", "Contacto eliminado"));

        verify(servicioContactos, times(1)).eliminarContacto(any(Contactos.class));
    }

    @Test
    void testBuscarContacto() throws Exception {
        when(servicioContactos.buscarContactoPorNombreOCorreo("Maria", "maria@example.com"))
                .thenReturn(Optional.of(contacto));

        mockMvc.perform(get("/api/contactos/buscar")
                        .param("nombre", "Maria")
                        .param("correo", "maria@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Maria"));

        verify(servicioContactos, times(1)).buscarContactoPorNombreOCorreo("Maria",
                "maria@example.com");

    }

}

