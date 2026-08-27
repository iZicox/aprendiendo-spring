package com.example.practica3.controller;

import com.example.practica3.dto.ProductoDTO;
import com.example.practica3.model.Producto;
import com.example.practica3.service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
@AllArgsConstructor
public class ProductoController {
    private ProductoService productoService;

    // listar todos
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> findAll() {

        return ResponseEntity.ok(productoService.findAll());
    }

    // listar por id
    @GetMapping("{id}")
    public ResponseEntity<ProductoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.findById(id));
    }

    // crear
    @PostMapping
    public ResponseEntity<ProductoDTO> create(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO nuevo = productoService.createProducto(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    //eliminar
    @DeleteMapping("{id}")
    public ResponseEntity<Map<String,String>> delete(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "producto elimado con id: " + id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductoDTO> update(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        Producto nuevo = Producto.builder()
                        .nombre(productoDTO.nombre())
                        .precio(productoDTO.precio())
                        .build();
        nuevo.setId(id);
        ProductoDTO editado = productoService.update(ProductoDTO.crearDto(nuevo));
        return ResponseEntity.ok(editado);
    }
}
