package com.springboot.practica_2.controller;

import com.springboot.practica_2.dto.ProductoDTO;
import com.springboot.practica_2.service.ProductoService;
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

    // listar todos los productos
    @GetMapping // esto se llama a la direccion raiz / usando el metodo get
    public ResponseEntity<List<ProductoDTO>> obtenerTodos(){
        return ResponseEntity.ok(productoService.listarTodos());
    }

    // listar por id
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    // crear nuevo producto
    @PostMapping
    public ResponseEntity<ProductoDTO> insertar(@RequestBody ProductoDTO productoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crear(productoDTO));
    }

    // eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id){
        productoService.borrarPorId(id);
        return ResponseEntity.ok(Map.of("mensaje", "Producto eliminado correctamente con id: " + id));
    }

    // actualizar
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizar(@PathVariable Long id, @RequestBody ProductoDTO productoDTO){
        ProductoDTO dtoConId = new ProductoDTO(id, productoDTO.nombre(), productoDTO.precio());
        return ResponseEntity.ok(productoService.editar(dtoConId));
    }



}
