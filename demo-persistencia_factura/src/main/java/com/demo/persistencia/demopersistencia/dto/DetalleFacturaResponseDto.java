package com.demo.persistencia.demopersistencia.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleFacturaResponseDto {

    private Long detalleId;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;

}
