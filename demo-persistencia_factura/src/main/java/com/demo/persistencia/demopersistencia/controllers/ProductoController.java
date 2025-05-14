package com.demo.persistencia.demopersistencia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.persistencia.demopersistencia.dto.ProductoResponseDto;
import com.demo.persistencia.demopersistencia.services.ProductoService;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") // pruebas locales

public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/guardar")
    public ProductoResponseDto guardarProducto(@RequestBody ProductoResponseDto dto) {
        return productoService.guardarProducto(dto);
    }

    @GetMapping("/listar")
    public List<ProductoResponseDto> obtenerTodosLosProductos() {
        return productoService.obtenerTodosLosProductos();
    }
}
