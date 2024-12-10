package com.aldob.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentasEvent {

    private String eventType;
    private CuentasDTO cuentas;

}
