package com.aldob.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

@RedisHash("Cuentas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuentas {

    @Id
    private Long idCuenta;
    private Long saldo;
    private Long deuda;
    private Date fechaCorte;
    private Long limiteCredito;
    private Boolean activo;

}
