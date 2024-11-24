package com.example.BibliotecaApi;

import com.example.BibliotecaApi.controller.LibroController;

import com.example.BibliotecaApi.entities.Autor;
import com.example.BibliotecaApi.entities.Libro;
import com.example.BibliotecaApi.service.LibroService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LibroControllerTest {

    @InjectMocks
    private LibroController libroController;

    @Mock
    private LibroService libroService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllLibros() {
        Autor autor1 = new Autor(1L, "NOMBRE1", "PAIS1");
        List<Libro> libros = new ArrayList<>();
        libros.add(new Libro(1L, "Titulo 1", "categoria1", true, autor1));
        libros.add(new Libro(2L, "Titulo 2", "categoria2", true, autor1));

        when(libroService.findAll()).thenReturn(libros);

        List<Libro> resultado = libroController.getAllLibros();

        assertEquals(2, resultado.size());
        verify(libroService, times(1)).findAll();
    }
}

