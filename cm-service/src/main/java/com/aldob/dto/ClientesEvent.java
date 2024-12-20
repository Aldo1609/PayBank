package com.aldob.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientesEvent {

    private String eventType;
    private ClientesDTO clientes;

}
