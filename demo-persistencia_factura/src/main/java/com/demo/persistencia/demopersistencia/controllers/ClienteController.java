package com.demo.persistencia.demopersistencia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.persistencia.demopersistencia.dto.ClienteResponseDto;
import com.demo.persistencia.demopersistencia.services.ClienteService;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*") // pruebas locales

public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/guardar")
    public ClienteResponseDto guardarCliente(@RequestBody ClienteResponseDto dto) {
        return clienteService.guardarCliente(dto);
    }

    @GetMapping("/listar")
    public List<ClienteResponseDto> obtenerTodosLosClientes() {
        return clienteService.obtenerTodosLosClientes();
    }
}
