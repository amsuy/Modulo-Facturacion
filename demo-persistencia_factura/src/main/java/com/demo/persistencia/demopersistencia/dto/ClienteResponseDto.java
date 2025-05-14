package com.demo.persistencia.demopersistencia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDto {

    private Long clienteId;
    private String nombre;
    private String direccion;
    private String nit;

}
