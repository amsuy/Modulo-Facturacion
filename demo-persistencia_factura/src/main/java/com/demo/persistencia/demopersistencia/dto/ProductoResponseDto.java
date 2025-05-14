package com.demo.persistencia.demopersistencia.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponseDto {

    private Long productoId;
    private String nombre;
    private BigDecimal precio;
    private Integer stock;

}
