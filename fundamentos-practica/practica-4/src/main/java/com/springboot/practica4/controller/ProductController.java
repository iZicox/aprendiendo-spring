package com.springboot.practica4.controller;

import com.springboot.practica4.dto.ProductoDTO;
import com.springboot.practica4.service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/productos")
public class ProductController {

    private final ProductoService productoService;

    @GetMapping("/saludo")
    public ResponseEntity<Map<String,String>> saludo() {
        return ResponseEntity.ok(Map.of("mensaje","hola"));
    }

    // crear
    @PostMapping
    public ResponseEntity<ProductoDTO> saludo(@RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crear(productoDTO));
    }

    // listar todos
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> productos() {
        return ResponseEntity.ok(productoService.findAll());
    }

    // listar id
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.findById(id));
    }

    // eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteById(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.ok(Map.of("mensaje","producto eliminado correctamente, id: " + id));
    }

    // modificar
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> update(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        ProductoDTO nuevo = new ProductoDTO(
                id,
                productoDTO.nombre(),
                productoDTO.precio()
        );
        return ResponseEntity.ok(productoService.actualizar(nuevo));
    }
}
