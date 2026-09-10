package com.example.practica3.dto;

import com.example.practica3.model.Producto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Optional;

public record ProductoDTO (
        Long id,
        @NotBlank(message = "el nombre es obligatorio") String nombre,
        @NotNull @Positive(message = "el precio debe ser mayor a 0") Double precio) {

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
