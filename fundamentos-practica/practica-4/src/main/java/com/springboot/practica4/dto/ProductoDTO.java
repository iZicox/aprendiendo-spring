package com.springboot.practica4.dto;

import com.springboot.practica4.model.Producto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductoDTO(
        Long id,
        @NotBlank String nombre,
        @Positive Double precio
) {
    public static ProductoDTO crearDto(Producto producto) {
        return new ProductoDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio());
    }

    public Producto crearEntidad(){
        return Producto.builder()
                .id(this.id)
                .nombre(this.nombre)
                .precio(this.precio)
                .build();
    }

    public void actualizarDto(Producto producto) {
        producto.setNombre(this.nombre);
        producto.setPrecio(this.precio);
    }
}
