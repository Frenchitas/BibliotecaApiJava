package com.example.BibliotecaApi.service;

import com.example.BibliotecaApi.entities.Autor;
import com.example.BibliotecaApi.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> findAll() {
        return autorRepository.findAll();
    }
}