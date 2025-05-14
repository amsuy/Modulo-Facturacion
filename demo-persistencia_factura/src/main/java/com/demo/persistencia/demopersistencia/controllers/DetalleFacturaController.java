package com.demo.persistencia.demopersistencia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.persistencia.demopersistencia.dto.DetalleFacturaRequest;
import com.demo.persistencia.demopersistencia.dto.DetalleFacturaResponseDto;
import com.demo.persistencia.demopersistencia.entidades.DetalleFactura;
import com.demo.persistencia.demopersistencia.services.DetalleFacturaService;

@RestController
@RequestMapping("/api/detalles-factura")
@CrossOrigin(origins = "*") // pruebas locales

public class DetalleFacturaController {

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @PostMapping("/guardar/{facturaId}")
    public void guardarDetalles(@PathVariable Long facturaId, @RequestBody List<DetalleFacturaRequest> detalles) {
        detalleFacturaService.guardarDetalles(facturaId, detalles);
    }

    @GetMapping("/listar")
    public List<DetalleFactura> listarDetalles() {
        return detalleFacturaService.obtenerTodosLosDetalles();
    }

    @GetMapping("/factura/{id}")
    public List<DetalleFacturaResponseDto> obtenerDetallesPorFactura(@PathVariable("id") Long facturaId) {
        return detalleFacturaService.obtenerDetallesPorFacturaId(facturaId);
    }

}
