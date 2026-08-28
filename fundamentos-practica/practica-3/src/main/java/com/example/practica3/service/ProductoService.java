package com.example.practica3.service;

import com.example.practica3.dto.ProductoDTO;

import java.util.List;
import java.util.Optional;


public interface ProductoService {

    // crear
    ProductoDTO createProducto(ProductoDTO productoDTO); // crud
    // listar
    List<ProductoDTO> findAll();
    // buscar por id
    Optional<ProductoDTO> findById(Long id);
    // eliminar por id
    void deleteById(Long id);
    // actualizar
    ProductoDTO update(ProductoDTO productoDTO);
}
