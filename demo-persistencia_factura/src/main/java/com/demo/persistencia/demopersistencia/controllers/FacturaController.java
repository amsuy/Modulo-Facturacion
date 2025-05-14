package com.demo.persistencia.demopersistencia.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.persistencia.demopersistencia.dto.FacturaDTO;
import com.demo.persistencia.demopersistencia.dto.FacturaRequest;
import com.demo.persistencia.demopersistencia.services.FacturaService;

@RestController
@RequestMapping("/api/facturas")
@CrossOrigin(origins = "*") // pruebas locales

public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping("/guardar")
    public FacturaDTO guardarFactura(@RequestBody FacturaRequest request) {
        return facturaService.guardarFactura(request);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<FacturaDTO> obtenerFacturasPorCliente(@PathVariable Long clienteId) {
        return facturaService.obtenerFacturasPorCliente(clienteId);
    }

    @GetMapping("/completa/{id}")
    public ResponseEntity<Map<String, Object>> obtenerFacturaCompleta(@PathVariable Long id) {
        return ResponseEntity.ok(facturaService.obtenerFacturaConDetalles(id));
    }

}
