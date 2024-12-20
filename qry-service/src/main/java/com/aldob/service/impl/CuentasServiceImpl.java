package com.aldob.service.impl;

import com.aldob.dto.CuentasEvent;
import com.aldob.model.Cuentas;
import com.aldob.repository.CuentasRepository;
import com.aldob.service.CuentasService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CuentasServiceImpl implements CuentasService {

    private final CuentasRepository cuentasRepository;

    @Override
    public Cuentas getDatosCuentaById(Long idCuenta){
        return cuentasRepository.findByIdCuenta(idCuenta);
    }

    @Override
    @KafkaListener(topics = "account-event-topic", groupId = "client-event-group")
    public void processCuentasEvents(CuentasEvent cuentasEvent){
        Cuentas cuentas = cuentasEvent.getCuentas();
        if (cuentasEvent.getEventType().equals("CreateCuenta")){
            cuentasRepository.save(cuentas);
        }
        if (cuentasEvent.getEventType().equals("UpdateCuenta")){
            Cuentas existingCuenta = cuentasRepository.findById(cuentas.getIdCuenta()).orElse(null);
            if (existingCuenta != null){
                existingCuenta.setSaldo(cuentas.getSaldo());
                existingCuenta.setDeuda(cuentas.getDeuda());
                existingCuenta.setFechaCorte(cuentas.getFechaCorte());
                existingCuenta.setLimiteCredito(cuentas.getLimiteCredito());
                existingCuenta.setActivo(cuentas.getActivo());
                cuentasRepository.save(existingCuenta);
            }
        }
        if (cuentasEvent.getEventType().equals("DeleteCuenta")){
            cuentasRepository.deleteById(cuentas.getIdCuenta());
        }

    }

}
