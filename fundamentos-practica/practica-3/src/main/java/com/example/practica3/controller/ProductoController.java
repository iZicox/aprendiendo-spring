package com.example.practica3.controller;

import com.example.practica3.dto.ProductoDTO;
import com.example.practica3.exception.ProductNotFoundException;
import com.example.practica3.model.Producto;
import com.example.practica3.service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@AllArgsConstructor
public class ProductoController {
    private ProductoService productoService;

    // listar todos
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> findAll() {
        List<ProductoDTO> listaDto = productoService.findAll();
        if(listaDto.isEmpty()) {

        }
        return ResponseEntity.ok(productoService.findAll());
    }

    // listar por id
    @GetMapping("{id}")
    public ResponseEntity<ProductoDTO> findById(@PathVariable Long id) {
        ProductoDTO buscado = productoService.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("No encontrado id: " + id));
        return ResponseEntity.ok(buscado);
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

    @GetMapping("/saludo")
    public ResponseEntity<Map<String,String>> saludo() {
        return ResponseEntity.ok(Map.of("message", "hola desde spring boot"));
    }

    @GetMapping("/saludo/{nombre}")
    public ResponseEntity<Map<String,String>> saludo(@PathVariable String nombre) {
        return ResponseEntity.ok(Map.of("message", "hola, " + nombre));
    }
}
