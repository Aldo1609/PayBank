package com.aldob.service.impl;

import com.aldob.dto.ClientesDTO;
import com.aldob.dto.ClientesEvent;
import com.aldob.dto.CuentasDTO;
import com.aldob.dto.CuentasEvent;
import com.aldob.exceptions.CuentasException;
import com.aldob.mapper.CuentasMapper;
import com.aldob.repository.ClientesRepository;
import com.aldob.repository.CuentasRepository;
import com.aldob.service.CuentasService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CuentasServiceImpl implements CuentasService {

    private final CuentasRepository cuentasRepository;
    private final ClientesRepository clientesRepository;
    private final CuentasMapper cuentasMapper;
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public CuentasDTO updateCuenta(Long idCuenta, CuentasDTO cuentasDTO) {
        try {
            return cuentasRepository.findById(idCuenta)
                    .map(cuentas -> {
                        cuentas.setSaldo(cuentasDTO.getSaldo());
                        cuentas.setDeuda(cuentasDTO.getDeuda());
                        cuentas.setFechaCorte(cuentasDTO.getFechaCorte());
                        cuentas.setLimiteCredito(cuentasDTO.getLimiteCredito());
                        cuentas.setActivo(cuentasDTO.getActivo());
                        CuentasDTO savedCuentasDTO = cuentasMapper.toDTO(cuentasRepository.save(cuentas));
                        kafkaTemplate.send("account-event-topic", new CuentasEvent("UpdateCuenta", savedCuentasDTO));
                        return savedCuentasDTO;
                    }).orElseThrow(() -> new RuntimeException("Cuenta no encontrada con id: " + idCuenta));
        } catch (Exception e) {
            throw new CuentasException("Error in updateCuenta: " + e.getMessage());
        }
    }

    @Override
    public String deleteCuenta(Long idCuenta) {
        try {
            return cuentasRepository.findById(idCuenta).map(cuentas -> {
                Long idCliente = cuentas.getClientes().getIdCliente();
                cuentasRepository.deleteById(idCuenta);
                clientesRepository.deleteById(idCliente);
                kafkaTemplate.send("account-event-topic", new CuentasEvent("DeleteCuenta", new CuentasDTO(idCuenta)));
                kafkaTemplate.send("client-event-topic", new ClientesEvent("DeleteCliente", new ClientesDTO(idCliente)));
                return "El cliente con id: " + idCliente + " y su cuenta con id: " + idCuenta + " fueron dados de baja correctamente!";
            }).orElseThrow(() -> new RuntimeException("Cuenta no encontrada con id: " + idCuenta));
        } catch (Exception e) {
            throw new CuentasException("Error in deleteCuenta: " + e.getMessage());
        }
    }
}