package com.example.biblioteca.controller;

// Importación de clases necesarias
import com.example.biblioteca.model.Autor;
import com.example.biblioteca.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Anotación que indica que esta clase es un controlador REST
@RestController
// Define la ruta base para todas las solicitudes manejadas por este controlador
@RequestMapping("/api/v1/autores")
public class AutorController {

    // Inyección automática del servicio que maneja la lógica de negocio para los autores
    @Autowired
    private AutorService autorService;

    // Maneja solicitudes GET a "/api/v1/autores"
    // Devuelve la lista completa de autores
    @GetMapping
    public List<Autor> getAllAutores() {
        return autorService.findAll();
    }

    // Maneja solicitudes GET a "/api/v1/autores/{id}"
    // Devuelve un autor específico según su ID
    @GetMapping("/{id}")
    public ResponseEntity<Autor> getAutorById(@PathVariable Long id) {
        Optional<Autor> autor = autorService.findById(id);

        // Si el autor existe, lo devuelve con código 200 OK; si no, responde con 404 Not Found
        return autor.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Maneja solicitudes POST a "/api/v1/autores"
    // Crea un nuevo autor a partir del objeto JSON recibido en el cuerpo de la solicitud
    @PostMapping
    public Autor createAutor(@RequestBody Autor autor) {
        return autorService.save(autor);
    }

    // Maneja solicitudes PUT a "/api/v1/autores/{id}"
    // Actualiza un autor existente con los datos proporcionados
    @PutMapping("/{id}")
    public ResponseEntity<Autor> updateAutor(@PathVariable Long id, @RequestBody Autor autorDetails) {
        Optional<Autor> autorOptional = autorService.findById(id);

        // Si el autor no existe, responde con 404 Not Found
        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Se actualizan los campos necesarios del autor encontrado
        Autor autor = autorOptional.get();
        autor.setNombre(autorDetails.getNombre());
        autor.setNacionalidad(autorDetails.getNacionalidad());

        // Guarda los cambios y devuelve el autor actualizado
        Autor updatedAutor = autorService.save(autor);
        return ResponseEntity.ok(updatedAutor);
    }

    // Maneja solicitudes DELETE a "/api/v1/autores/{id}"
    // Elimina un autor por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutor(@PathVariable Long id) {
        // Si el autor no existe, responde con 404 Not Found
        if (autorService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Elimina el autor y responde con 204 No Content
        autorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

