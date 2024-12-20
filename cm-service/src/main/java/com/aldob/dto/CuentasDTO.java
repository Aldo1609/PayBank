package com.aldob.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentasDTO {

    private Long idCuenta;
    private Long saldo;
    private Long deuda;
    private Date fechaCorte;
    private Long limiteCredito;
    private Boolean activo;

    public CuentasDTO(Long idCuenta) {
        this.idCuenta = idCuenta;
    }

}