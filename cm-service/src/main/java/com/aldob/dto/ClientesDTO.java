package com.aldob.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientesDTO {
    private Long idCliente;
    private String nombre;
    private String apellido;
    private byte genero;
    private String direccion;
    private String telefono;
    private Date fecRegistro;

    public ClientesDTO(Long idCliente) {
        this.idCliente = idCliente;
    }
}