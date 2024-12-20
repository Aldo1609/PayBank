package com.aldob.mapper;

import com.aldob.dto.CuentasDTO;
import com.aldob.model.Cuentas;
import org.springframework.stereotype.Component;

@Component
public class CuentasMapper {

    public CuentasDTO toDTO(Cuentas cuentas){
        CuentasDTO dto = new CuentasDTO();
        dto.setIdCuenta(cuentas.getIdCuenta());
        dto.setSaldo(cuentas.getSaldo());
        dto.setDeuda(cuentas.getDeuda());
        dto.setFechaCorte(cuentas.getFechaCorte());
        dto.setLimiteCredito(cuentas.getLimiteCredito());
        dto.setActivo(cuentas.getActivo());
        return dto;
    }

}
