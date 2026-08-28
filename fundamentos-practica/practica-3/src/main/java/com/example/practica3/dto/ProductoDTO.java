package com.example.practica3.dto;

import com.example.practica3.model.Producto;

import java.util.Optional;

public record ProductoDTO (Long id, String nombre, Double precio) {

    // para leer datos
    // convertir a dto
    public static ProductoDTO crearDto (Producto producto) {
        return new ProductoDTO(producto.getId(), producto.getNombre(), producto.getPrecio());
    }

    // para crear un nuevo registro
    // convertir a entidad
    public Producto crearEntidad (){
        return Producto.builder()
                .nombre(this.nombre)
                .precio(this.precio)
                .build();
    }


    // actualizar entidad
    public void actualizarEntidad (Producto producto) {
        producto.setNombre(this.nombre);
        producto.setPrecio(this.precio);
    }


}
