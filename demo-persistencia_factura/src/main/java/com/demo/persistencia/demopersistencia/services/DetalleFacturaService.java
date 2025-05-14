package com.demo.persistencia.demopersistencia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.persistencia.demopersistencia.dto.DetalleFacturaRequest;
import com.demo.persistencia.demopersistencia.dto.DetalleFacturaResponseDto;
import com.demo.persistencia.demopersistencia.entidades.DetalleFactura;
import com.demo.persistencia.demopersistencia.entidades.Factura;
import com.demo.persistencia.demopersistencia.entidades.Producto;
import com.demo.persistencia.demopersistencia.repositorio.DetalleFacturaRepository;
import com.demo.persistencia.demopersistencia.repositorio.FacturaCustomRepository;
import com.demo.persistencia.demopersistencia.repositorio.ProductoRepository;

@Service
public class DetalleFacturaService {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    @Autowired
    private FacturaCustomRepository facturaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public void guardarDetalles(Long facturaId, List<DetalleFacturaRequest> detalles) {
        Factura factura = facturaRepository.findById(facturaId)
                .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada"));

        for (DetalleFacturaRequest dto : detalles) {
            Producto producto = productoRepository.findById(dto.getProductoId())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

            DetalleFactura detalle = new DetalleFactura();
            detalle.setFactura(factura);
            detalle.setProducto(producto);
            detalle.setCantidad(dto.getCantidad());
            detalle.setPrecioUnitario(dto.getPrecioUnitario());

            detalleFacturaRepository.save(detalle);
        }
    }

    public List<DetalleFactura> obtenerTodosLosDetalles() {
        return detalleFacturaRepository.findAll();
    }

    public List<DetalleFacturaResponseDto> obtenerDetallesPorFacturaId(Long facturaId) {
        return detalleFacturaRepository.findByFacturaId(facturaId);
    }

}
