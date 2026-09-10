package com.springboot.practica4.service;

import com.springboot.practica4.dto.ProductoDTO;

import java.util.List;
import java.util.Optional;

public interface ProductoService{
    List<ProductoDTO> findAll();
    ProductoDTO findById(Long id);
    ProductoDTO crear(ProductoDTO productoDTO);
    ProductoDTO actualizar(ProductoDTO productoDTO);
    void eliminar(Long id);
}
