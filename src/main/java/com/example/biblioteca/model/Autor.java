package com.example.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity // Marca esta clase como una entidad JPA, lo que significa que se mapeará a una tabla en la base de datos
public class Autor {

    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La base de datos generará automáticamente el valor del ID
    @JsonManagedReference
    private Long id;

    private String nombre; // Nombre del autor
    private String nacionalidad; // Nacionalidad del autor

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    // Relación uno-a-muchos: un autor puede tener muchos libros
    // mappedBy = "autor": el campo "autor" en la clase Libro es el dueño de la relación
    // cascade = ALL: las operaciones como persistir o eliminar se aplican también a los libros asociados
    // orphanRemoval = true: si un libro se elimina de la lista, se elimina también de la base de datos
    private List<Libro> libros;

    // Constructor vacío requerido por JPA
    public Autor() {}

    // Constructor con parámetros para facilitar la creación de instancias
    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    // Métodos getter y setter para acceder y modificar los campos

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    public List<Libro> getLibros() { return libros; }
    public void setLibros(List<Libro> libros) { this.libros = libros; }
}
