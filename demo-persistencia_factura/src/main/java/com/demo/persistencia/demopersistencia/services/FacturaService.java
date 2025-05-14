package com.demo.persistencia.demopersistencia.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.persistencia.demopersistencia.dto.DetalleFacturaRequest;
import com.demo.persistencia.demopersistencia.dto.DetalleFacturaResponseDto;
import com.demo.persistencia.demopersistencia.dto.FacturaDTO;
import com.demo.persistencia.demopersistencia.dto.FacturaRequest;
import com.demo.persistencia.demopersistencia.entidades.Cliente;
import com.demo.persistencia.demopersistencia.entidades.DetalleFactura;
import com.demo.persistencia.demopersistencia.entidades.Factura;
import com.demo.persistencia.demopersistencia.entidades.Producto;
import com.demo.persistencia.demopersistencia.repositorio.ClienteRepository;
import com.demo.persistencia.demopersistencia.repositorio.DetalleFacturaRepository;
import com.demo.persistencia.demopersistencia.repositorio.FacturaCustomRepository;
import com.demo.persistencia.demopersistencia.repositorio.ProductoRepository;

@Service
public class FacturaService {

    @Autowired
    private FacturaCustomRepository facturaRepository;

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public FacturaDTO guardarFactura(FacturaRequest request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        Factura factura = new Factura();
        factura.setFechaFactura(request.getFechaFactura());
        factura.setCliente(cliente);
        factura.setTotal(request.getTotal());

        Factura facturaGuardada = facturaRepository.save(factura);

        for (DetalleFacturaRequest detalleReq : request.getDetalles()) {
            Producto producto = productoRepository.findById(detalleReq.getProductoId())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

            DetalleFactura detalle = new DetalleFactura();
            detalle.setFactura(facturaGuardada);
            detalle.setProducto(producto);
            detalle.setCantidad(detalleReq.getCantidad());
            detalle.setPrecioUnitario(detalleReq.getPrecioUnitario());

            detalleFacturaRepository.save(detalle);
        }

        return new FacturaDTO(
                facturaGuardada.getFacturaId(),
                facturaGuardada.getFechaFactura(),
                cliente.getNombre(),
                facturaGuardada.getTotal(),
                request.getDetalles().stream().mapToInt(DetalleFacturaRequest::getCantidad).sum(),
                request.getDetalles().stream().map(DetalleFacturaRequest::getPrecioUnitario).findFirst().orElse(null));
    }

    public List<FacturaDTO> obtenerFacturasPorCliente(Long clienteId) {
        List<Object[]> datos = facturaRepository.getFacturasCliente(clienteId);
        List<FacturaDTO> facturas = new ArrayList<>();

        for (Object[] row : datos) {
            FacturaDTO dto = new FacturaDTO();
            dto.setFacturaId((Long) row[0]);
            dto.setFechaFactura((java.time.LocalDate) row[1]);
            dto.setNombreCliente((String) row[2]);
            dto.setTotal((java.math.BigDecimal) row[3]);
            dto.setCantidadProducto((Integer) row[4]);
            dto.setPrecioUnitario((java.math.BigDecimal) row[5]);
            facturas.add(dto);
        }

        return facturas;
    }

    public Map<String, Object> obtenerFacturaConDetalles(Long facturaId) {
        List<Object[]> datos = facturaRepository.obtenerFacturaConDetalles(facturaId);

        if (datos.isEmpty()) {
            throw new RuntimeException("Factura no encontrada");
        }

        Object[] fila = datos.get(0);
        FacturaDTO facturaDTO = new FacturaDTO();
        facturaDTO.setFacturaId((Long) fila[0]);
        facturaDTO.setFechaFactura((LocalDate) fila[1]);
        facturaDTO.setNombreCliente((String) fila[2]);
        facturaDTO.setTotal((BigDecimal) fila[3]);

        List<DetalleFacturaResponseDto> detalles = new ArrayList<>();
        for (Object[] obj : datos) {
            DetalleFacturaResponseDto detalle = new DetalleFacturaResponseDto();
            detalle.setDetalleId((Long) obj[4]);
            detalle.setNombreProducto((String) obj[5]);
            detalle.setCantidad((Integer) obj[6]);
            detalle.setPrecioUnitario((BigDecimal) obj[7]);
            detalles.add(detalle);
        }

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("factura", facturaDTO);
        respuesta.put("detalles", detalles);

        return respuesta;
    }

}
