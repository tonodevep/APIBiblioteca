# 📚 API REST - Biblioteca con Spring Boot

🚀 Este proyecto demuestra cómo aplicar relaciones entre entidades, filtros y ordenación utilizando **Spring Boot** y buenas prácticas de desarrollo backend.


## 🎯 Objetivo

Aplicar relaciones entre entidades, búsquedas avanzadas y ordenación en una API REST usando Spring Boot.

 

## 🧭 Finalidad

Construir una API REST más completa que incluya:

✅ Dos entidades relacionadas (`@OneToMany` / `@ManyToOne`)  
✅ Operaciones **CRUD** completas  
✅ Filtros y ordenación usando `@RequestParam`  
✅ Pruebas de endpoints con **Postman**  
✅ Rutas **versionadas** (`/api/v1/...`)

 

## 🛠️ Requisitos

Crea un proyecto Spring Boot con las siguientes entidades:

### ✍️ Entidad Autor

| Campo         | Tipo            | Descripción                |
|---------------|------------------|----------------------------|
| `id`          | `Long`           | Autogenerado               |
| `nombre`      | `String`         | Nombre del autor           |
| `nacionalidad`| `String`         | Nacionalidad del autor     |
| `libros`      | `List<Libro>`    | Relación @OneToMany        |

### 📘 Entidad Libro

| Campo             | Tipo      | Descripción                         |
|-------------------|-----------|-------------------------------------|
| `id`              | `Long`    | Autogenerado                        |
| `titulo`          | `String`  | Título del libro                    |
| `isbn`            | `String`  | Código ISBN del libro               |
| `anioPublicacion` | `int`     | Año en que se publicó               |
| `autor`           | `Autor`   | Relación con Autor (@ManyToOne)     |

 

## 🔗 Relación entre entidades

📚 Un **Autor** puede tener varios **Libros** → `@OneToMany(mappedBy = "autor")`  
📖 Un **Libro** pertenece a un único **Autor** → `@ManyToOne`

 

## 🔥 Endpoints mínimos requeridos

### 📘 Libro

| Método | Ruta                          | Función                          |
|--------|-------------------------------|----------------------------------|
| GET    | `/api/v1/libros`             | Listar todos los libros          |
| GET    | `/api/v1/libros/{id}`        | Obtener libro por ID             |
| POST   | `/api/v1/libros`             | Crear libro (vincular autor)     |
| PUT    | `/api/v1/libros/{id}`        | Actualizar un libro              |
| DELETE | `/api/v1/libros/{id}`        | Eliminar un libro                |
| GET    | `/api/v1/libros/buscar`      | Buscar con filtros y ordenación  |

 

## 🧪 Parámetros de búsqueda en `/buscar`

| Parámetro | Descripción                             | Ejemplo                           |
|-----------|-----------------------------------------|-----------------------------------|
| `titulo`  | Filtrar por título parcial              | `/buscar?titulo=Java`            |
| `anio`    | Filtrar por año de publicación          | `/buscar?anio=2020`              |
| `sortBy`  | Campo de ordenación                     | `/buscar?sortBy=anioPublicacion` |
| `order`   | Dirección de orden: `asc` o `desc`      | `/buscar?order=desc`             |

### 🧵 Combinaciones útiles

- `/api/v1/libros/buscar?titulo=Java`  
- `/api/v1/libros/buscar?anio=2020`  
- `/api/v1/libros/buscar?sortBy=anioPublicacion&order=desc`  
- `/api/v1/libros/buscar?titulo=Java&anio=2020&sortBy=anioPublicacion&order=asc`

## 📬 Pruebas con Postman

Importaremos una colección en Postman para probar todos los endpoints.  

## 🏁 ¡Eso es todo!

Así tendremos nuestra biblioteca!!

## Autores
Este proyecto ha sido desarrollado por Sara Alonso Perdomo y Juan Antonio "Toño" Tejera González.  

Github Sara: [![Web](https://img.shields.io/badge/GitHub-juniuun-14a1f0?style=for-the-badge&logo=github&logoColor=white&labelColor=101010)](https://github.com/juniuun/)

Github de Toño: [![Web](https://img.shields.io/badge/GitHub-tonodevep-14a1f0?style=for-the-badge&logo=github&logoColor=white&labelColor=101010)](https://github.com/tonodevep/)
