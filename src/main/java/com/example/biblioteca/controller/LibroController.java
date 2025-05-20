package com.example.biblioteca.controller;

//Imports
import com.example.biblioteca.model.Libro;
import com.example.biblioteca.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//Anotación de la ruta de libros
@RequestMapping("/api/v1/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;

    //Obtener todos los libros
    @GetMapping
    public List<Libro> getAllLibros() {
        return libroService.findAll();
    }

    //Obtener libros por id
    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
        Optional<Libro> libro = libroService.findById(id);
        return libro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear libro
    @PostMapping
    public Libro createLibro(@RequestBody Libro libro) {
        return libroService.save(libro);
    }

    //Actualizar libro
    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libroDetails) {
        Optional<Libro> libroOptional = libroService.findById(id);
        if (libroOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Libro libro = libroOptional.get();
        libro.setTitulo(libroDetails.getTitulo());
        libro.setIsbn(libroDetails.getIsbn());
        libro.setAnioPublicacion(libroDetails.getAnioPublicacion());
        libro.setAutor(libroDetails.getAutor());
        
        Libro updatedLibro = libroService.save(libro);
        return ResponseEntity.ok(updatedLibro);
    }

    //Borrar libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        if (libroService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        libroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //Buscar libro
    @GetMapping("/buscar")
    public List<Libro> buscarLibros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Integer anio,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order) {
        
        return libroService.buscarLibros(titulo, anio, sortBy, order);
    }
}
