package com.example.BibliotecaApi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Libro {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String titulo;
    private String categoria;
    private boolean disponible;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
}
