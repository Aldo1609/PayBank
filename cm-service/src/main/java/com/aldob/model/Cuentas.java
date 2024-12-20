package com.aldob.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "Cuentas")
public class Cuentas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuenta;

    @ManyToOne
    @JoinColumn(name = "idCuenta", insertable = false, updatable = false)
    private Clientes clientes;

    @NotNull(message = "El saldo no puede ser nulo")
    @Min(value = 0, message = "El saldo debe ser mayor o igual a 0")
    private Long saldo;

    @NotNull(message = "La deuda no puede ser nula")
    @Min(value = 0, message = "La deuda debe ser mayor o igual 0")
    private Long deuda;

    @NotNull(message = "La fecha de corte no puede ser nula")
    @PastOrPresent(message = "La fecha de corte debe ser valida")
    private Date fechaCorte;

    private Long limiteCredito;

    @NotNull(message = "El campo activo no puede ser nulo")
    private Boolean activo;
}