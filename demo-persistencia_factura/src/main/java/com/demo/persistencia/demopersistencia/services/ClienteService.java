package com.demo.persistencia.demopersistencia.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.persistencia.demopersistencia.dto.ClienteResponseDto;
import com.demo.persistencia.demopersistencia.entidades.Cliente;
import com.demo.persistencia.demopersistencia.repositorio.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteResponseDto guardarCliente(ClienteResponseDto dto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setDireccion(dto.getDireccion());
        cliente.setNit(dto.getNit());

        Cliente guardado = clienteRepository.save(cliente);

        return new ClienteResponseDto(
            guardado.getClienteId(),
            guardado.getNombre(),
            guardado.getDireccion(),
            guardado.getNit()
        );
    }

    public List<ClienteResponseDto> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream()
                .map(c -> new ClienteResponseDto(
                    c.getClienteId(),
                    c.getNombre(),
                    c.getDireccion(),
                    c.getNit()))
                .collect(Collectors.toList());
    }
}
