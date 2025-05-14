package com.demo.persistencia.demopersistencia.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.persistencia.demopersistencia.dto.ProductoResponseDto;
import com.demo.persistencia.demopersistencia.entidades.Producto;
import com.demo.persistencia.demopersistencia.repositorio.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public ProductoResponseDto guardarProducto(ProductoResponseDto dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());

        Producto guardado = productoRepository.save(producto);

        return new ProductoResponseDto(
            guardado.getProductoId(),
            guardado.getNombre(),
            guardado.getPrecio(),
            guardado.getStock()
        );
    }

    public List<ProductoResponseDto> obtenerTodosLosProductos() {
        List<Producto> productos = productoRepository.findAll();

        return productos.stream()
                .map(p -> new ProductoResponseDto(
                    p.getProductoId(),
                    p.getNombre(),
                    p.getPrecio(),
                    p.getStock()))
                .collect(Collectors.toList());
    }
}
