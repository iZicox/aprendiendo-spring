package com.springboot.practica4.service;

import com.springboot.practica4.dto.ProductoDTO;
import com.springboot.practica4.exception.ProductListException;
import com.springboot.practica4.exception.ProductNotFoundException;
import com.springboot.practica4.model.Producto;
import com.springboot.practica4.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> findAll() {

        List<ProductoDTO> lista = productoRepository.findAll().stream()

                .map(ProductoDTO::crearDto)

                .toList();

        if(lista.isEmpty()){
            throw new ProductListException("No se encontro ningun producto");
        }

        return  lista;
    }

    @Override
    public ProductoDTO findById(Long id) {

        return productoRepository.findById(id)
                .map(ProductoDTO::crearDto)
                .orElseThrow(() -> new ProductNotFoundException("No existe el producto con el id: " + id));
    }

    @Override
    public ProductoDTO crear(ProductoDTO productoDTO) {

        return ProductoDTO.crearDto(
                productoRepository.save(productoDTO.crearEntidad())
        );
    }


    @Override
    public ProductoDTO actualizar(ProductoDTO productoDTO) {

        Producto original = productoRepository.findById(productoDTO.id())
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "No existe el producto con el id: " + productoDTO.id()
                        )
                );
        productoDTO.actualizarDto(original);

        Producto editado = productoRepository.save(original);

        return  ProductoDTO.crearDto(editado);
    }

    @Override
    public void eliminar(Long id) {
        if(!productoRepository.existsById(id)){
            throw new ProductNotFoundException("No existe el producto con el id: " + id);
        }
        productoRepository.deleteById(id);
    }
}
