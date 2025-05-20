package com.example.biblioteca.repository;

//Imports
import com.example.biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long>, JpaSpecificationExecutor<Libro> {
    
    //Para buscar libros según la cadena específica
    List<Libro> findByTituloContaining(String titulo);
    List<Libro> findByTituloContaining(String titulo, Sort sort);
    
    List<Libro> findByAnioPublicacion(int anio);
    List<Libro> findByAnioPublicacion(int anio, Sort sort);
    
    List<Libro> findByTituloContainingAndAnioPublicacion(String titulo, int anio);
    List<Libro> findByTituloContainingAndAnioPublicacion(String titulo, int anio, Sort sort);
    
    
    Page<Libro> findByTituloContaining(String titulo, Pageable pageable);
    Page<Libro> findByAnioPublicacion(int anio, Pageable pageable);
    Page<Libro> findByTituloContainingAndAnioPublicacion(String titulo, int anio, Pageable pageable);
}
