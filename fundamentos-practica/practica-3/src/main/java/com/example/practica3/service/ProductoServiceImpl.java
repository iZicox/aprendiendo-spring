package com.example.practica3.service;

import com.example.practica3.dto.ProductoDTO;
import com.example.practica3.model.Producto;
import com.example.practica3.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private ProductoRepository productoRepository;

    @Override
    public ProductoDTO createProducto(ProductoDTO productoDTO) {
        Producto nuevo = productoDTO.crearEntidad();
        productoRepository.save(nuevo);
        return ProductoDTO.crearDto(nuevo);
    }

    @Override
    public List<ProductoDTO> findAll() {
        List<ProductoDTO> lista = productoRepository.findAll()  // esto nos devuelve la lista de Producto
                .stream()
                .map(ProductoDTO::crearDto) // lo pasamos por el metodo estatico para convertir de entidad a dto
                .toList();                  // convertimos todo a la lista
        return lista;
    }

    @Override
    public Optional<ProductoDTO> findById(Long id) {
        return productoRepository.findById(id)
                .map(ProductoDTO::crearDto);
    }

    @Override
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public ProductoDTO update(ProductoDTO productoDTO) {
        Producto editado = productoRepository.save(productoDTO.crearEntidad());
        return ProductoDTO.crearDto(editado);
    }
}
