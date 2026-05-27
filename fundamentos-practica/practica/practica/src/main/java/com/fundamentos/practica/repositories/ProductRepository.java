package com.fundamentos.practica.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fundamentos.practica.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {


    @Query("select distinct p.categoria from Product p")
    List<String> listarCategorias();

    List<Product> findByCategoriaIgnoreCase(String cat);

    Optional<Product> findById(Long id);







}
