package com.aldob.dto;

import com.aldob.model.Clientes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentasDTO {

    private Long idCuenta;
    private Long idCliente;
    private Clientes clientes;
    private Long saldo;
    private Long deuda;
    private Date fechaCorte;
    private Boolean activo;

}