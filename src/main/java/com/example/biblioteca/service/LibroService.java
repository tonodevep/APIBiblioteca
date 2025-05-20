package com.example.biblioteca.service;

import com.example.biblioteca.model.Autor;
import com.example.biblioteca.model.Libro;
import com.example.biblioteca.repository.AutorRepository;
import com.example.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;
    private final AutorRepository autorRepository;

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
        // 👇 Esta es la lógica del paso 3: buscar el autor por su ID y asignarlo completo
        if (libro.getAutor() != null && libro.getAutor().getId() != null) {
            Optional<Autor> autorOptional = autorRepository.findById(libro.getAutor().getId());
            if (autorOptional.isPresent()) {
                libro.setAutor(autorOptional.get());
            } else {
                throw new RuntimeException("Autor con ID " + libro.getAutor().getId() + " no encontrado.");
            }
        }
        return libroRepository.save(libro);
    }


    public void deleteById(Long id) {
        libroRepository.deleteById(id);
    }

    public List<Libro> buscarLibros(String titulo, Integer anio, String sortBy, String order) {
        if (titulo != null && anio != null) {
            if (sortBy != null) {
                Sort sort = Sort.by(order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
                return libroRepository.findByTituloContainingAndAnioPublicacion(titulo, anio, sort);
            }
            return libroRepository.findByTituloContainingAndAnioPublicacion(titulo, anio);
        } else if (titulo != null) {
            if (sortBy != null) {
                Sort sort = Sort.by(order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
                return libroRepository.findByTituloContaining(titulo, sort);
            }
            return libroRepository.findByTituloContaining(titulo);
        } else if (anio != null) {
            if (sortBy != null) {
                Sort sort = Sort.by(order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
                return libroRepository.findByAnioPublicacion(anio, sort);
            }
            return libroRepository.findByAnioPublicacion(anio);
        } else if (sortBy != null) {
            Sort sort = Sort.by(order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            return libroRepository.findAll(sort);
        }
        return libroRepository.findAll();
    }
}
