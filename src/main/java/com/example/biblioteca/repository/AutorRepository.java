package com.example.biblioteca.repository;

import com.example.biblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

// Esta interfaz extiende JpaRepository, que proporciona métodos CRUD y más para trabajar con entidades JPA
// <Autor, Long> indica que trabajará con la entidad Autor y que su clave primaria (ID) es de tipo Long

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
