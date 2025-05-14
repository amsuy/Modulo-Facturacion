package com.demo.persistencia.demopersistencia.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.persistencia.demopersistencia.dto.DetalleFacturaResponseDto;
import com.demo.persistencia.demopersistencia.entidades.DetalleFactura;

public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {

    @Query("SELECT new com.demo.persistencia.demopersistencia.dto.DetalleFacturaResponseDto( " +
            "d.detalleId, p.nombre, d.cantidad, d.precioUnitario) " +
            "FROM DetalleFactura d JOIN d.producto p " +
            "WHERE d.factura.facturaId = :facturaId")
    List<DetalleFacturaResponseDto> findByFacturaId(@Param("facturaId") Long facturaId);

}
