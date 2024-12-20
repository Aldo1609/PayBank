package com.aldob.dto;

import com.aldob.model.Cuentas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentasEvent {
    private String eventType;
    private Cuentas cuentas;
}
