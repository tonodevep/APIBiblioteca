package com.example.biblioteca.service;

import com.example.biblioteca.model.Autor;
import com.example.biblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Indica que esta clase es un servicio de Spring (capa de lógica de negocio)
public class AutorService {

    // Inyección del repositorio de autores para acceder a la base de datos
    @Autowired
    private AutorRepository autorRepository;

    // Devuelve todos los autores almacenados en la base de datos
    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    // Busca un autor por su ID. Devuelve un Optional que puede contener o no un autor
    public Optional<Autor> findById(Long id) {
        return autorRepository.findById(id);
    }

    // Guarda un nuevo autor o actualiza uno existente en la base de datos
    public Autor save(Autor autor) {
        return autorRepository.save(autor);
    }

    // Elimina un autor por su ID
    public void deleteById(Long id) {
        autorRepository.deleteById(id);
    }
}
