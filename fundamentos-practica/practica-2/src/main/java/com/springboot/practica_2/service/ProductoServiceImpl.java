package com.springboot.practica_2.service;

import com.springboot.practica_2.dto.ProductoDTO;
import com.springboot.practica_2.exception.ProductoNoEncontrado;
import com.springboot.practica_2.model.Producto;
import com.springboot.practica_2.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> listarTodos() {
        return productoRepository.findAll().stream()    // devuelve una lista de la entidad
                .map(ProductoDTO::crearDTO)             // convierte las entidades a dto
                .toList();                              // exportar a lista
    }

    @Override
    public ProductoDTO buscarPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontrado("No existe el producto con el id: " + id)); // si se dispara este error el metodo se detiene aqui
        return ProductoDTO.crearDTO(producto);
    }

    @Override
    public ProductoDTO buscarPorNombre(String nombre) {
        Producto producto = productoRepository.findByNombre(nombre)
                .orElseThrow(() -> new ProductoNoEncontrado("No existe el producto con nombre: " + nombre)); // si se dispara este error el metodo se detiene aqui
        return ProductoDTO.crearDTO(producto);
    }

    @Override
    public ProductoDTO crear(ProductoDTO productoDTO) {
        Producto nuevo = productoDTO.crearProducto();
        Producto guardado = productoRepository.save(nuevo);
        return ProductoDTO.crearDTO(guardado);
    }

    @Override
    public ProductoDTO editar(ProductoDTO productoDTO) {
        Producto existe = productoRepository.findById(productoDTO.id())
                .orElseThrow(() -> new ProductoNoEncontrado("No existe el producto con id: " + productoDTO.id())); //// si se dispara este error el metodo se detiene aqui
        productoDTO.editar(existe);
        Producto actualizado = productoRepository.save(existe);
        return ProductoDTO.crearDTO(actualizado);
    }

    @Override
    public void borrarPorId(Long id) {
        Producto existe = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontrado("No existe el producto con id: " + id)); //// si se dispara este error el metodo se detiene aqui
        productoRepository.delete(existe);

    }
}
