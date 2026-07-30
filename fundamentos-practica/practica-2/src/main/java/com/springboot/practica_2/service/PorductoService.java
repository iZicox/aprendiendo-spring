package com.springboot.practica_2.service;

import com.springboot.practica_2.dto.ProductoDTO;
import com.springboot.practica_2.model.Producto;

import java.util.List;

public interface PorductoService {
    List<ProductoDTO> listarTodos();
    ProductoDTO buscarPorId(Long id);
    ProductoDTO buscarPorNombre(String nombre);
    ProductoDTO crear(ProductoDTO productoDTO);
    ProductoDTO editar(ProductoDTO productoDTO);
    void borrarPorId(Long id);
}
