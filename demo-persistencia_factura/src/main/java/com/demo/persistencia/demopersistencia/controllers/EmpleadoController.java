package com.demo.persistencia.demopersistencia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.persistencia.demopersistencia.dto.EmpleadoDto;
import com.demo.persistencia.demopersistencia.services.EmpleadoServicio;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "*") // pruebas locales

public class EmpleadoController {

    @Autowired
    private EmpleadoServicio empleadoServicio;

    @PostMapping("/guardar")
    public EmpleadoDto guardarEmpleado(@RequestBody EmpleadoDto dto) {
        return empleadoServicio.guardarEmpleado(dto);
    }

    @GetMapping("/listar")
    public List<EmpleadoDto> obtenerTodosLosEmpleados() {
        return empleadoServicio.obtenerTodosLosEmpleados();
    }
}
