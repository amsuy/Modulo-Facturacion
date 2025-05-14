package com.demo.persistencia.demopersistencia.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.persistencia.demopersistencia.dto.EmpleadoDto;
import com.demo.persistencia.demopersistencia.entidades.Empleados;
import com.demo.persistencia.demopersistencia.repositorio.EmpleadoRepositorio;

@Service
public class EmpleadoServicio {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    public EmpleadoDto guardarEmpleado(EmpleadoDto dto) {
        Empleados empleado = new Empleados();
        empleado.setNombreEmpleado(dto.getNombreEmpleado());
        empleado.setDireccion(dto.getDireccion());
        empleado.setEdad(dto.getEdad());
        empleado.setPuesto(dto.getPuesto());

        Empleados guardado = empleadoRepositorio.save(empleado);

        return new EmpleadoDto(
            guardado.getIdEmpleado(),
            guardado.getNombreEmpleado(),
            guardado.getDireccion(),
            guardado.getEdad(),
            guardado.getPuesto()
        );
    }

    public List<EmpleadoDto> obtenerTodosLosEmpleados() {
        List<Empleados> empleados = (List<Empleados>) empleadoRepositorio.findAll();

        return empleados.stream()
                .map(e -> new EmpleadoDto(
                    e.getIdEmpleado(),
                    e.getNombreEmpleado(),
                    e.getDireccion(),
                    e.getEdad(),
                    e.getPuesto()))
                .collect(Collectors.toList());
    }
}
