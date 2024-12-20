package com.aldob.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

@RedisHash("Clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clientes {
    @Id
    private Long idCliente;
    private String nombre;
    private String apellido;
    private byte genero;
    private String direccion;
    private String telefono;
    private Date fecRegistro;

}