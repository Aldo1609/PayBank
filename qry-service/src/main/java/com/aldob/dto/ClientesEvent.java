package com.aldob.dto;

import com.aldob.model.Clientes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientesEvent {
    private String eventType;
    private Clientes clientes;
}
