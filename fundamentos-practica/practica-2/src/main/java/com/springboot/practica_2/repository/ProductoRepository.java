package com.springboot.practica_2.repository;

import com.springboot.practica_2.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombre(String nombre);
}
