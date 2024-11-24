package com.example.BibliotecaApi.service;

import com.example.BibliotecaApi.entities.Autor;
import com.example.BibliotecaApi.entities.Libro;
import com.example.BibliotecaApi.repository.LibroRepository;
import com.example.BibliotecaApi.repository.AutorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }


    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    public Optional<Libro> findById(Long id) {
        return libroRepository.findById(id);
    }

    public Libro save(Libro libro) {
        // Verificar si el autor existe
        if (libro.getAutor() != null && libro.getAutor().getId() != null) {
            Autor autor = autorRepository.findById(libro.getAutor().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado"));
            libro.setAutor(autor);  // Asignar el autor al libro
        } else {
            throw new IllegalArgumentException("El autor debe ser válido");
        }
        return libroRepository.save(libro);  // Guardar el libro
    }


    public void deleteById(Long id) {
        libroRepository.deleteById(id);
    }

    public List<Libro> findByAutorId(Long idAutor) {
        return findAll().stream()
                .filter(libro -> libro.getAutor().getId().equals(idAutor))
                .collect(Collectors.toList());
    }

    public List<String> findDistinctCategorias() {
        return libroRepository.findAll()
                .stream()
                .map(Libro::getCategoria)
                .distinct()
                .toList();
    }


    public List<Libro> findByCategoria(String categoria) {
        return libroRepository.findByCategoria(categoria);
    }
}