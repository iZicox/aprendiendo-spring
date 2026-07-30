package com.springboot.practica_2.dto;

import com.springboot.practica_2.model.Producto;

public record ProductoDTO(
        Long id, String nombre, double precio
) {

    // entidad a dto
    public static ProductoDTO crearDTO(Producto producto) {
        return new ProductoDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio()
        );
    }

    // dto a producto
    public Producto crearProducto() {
        return Producto.builder()
                .nombre(this.nombre)
                .precio(this.precio)
                .build() ;
    }

    // editar
    public static void editar(Producto producto) {
        producto.setNombre(producto.getNombre());
        producto.setPrecio(producto.getPrecio());
    }
}
