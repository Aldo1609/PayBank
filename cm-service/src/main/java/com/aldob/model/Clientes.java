package com.aldob.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "Clientes")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Min(value = 0, message = "El genero debe ser 0 o 1")
    @Max(value = 1, message = "El genero debe ser 0 o 1")
    private byte genero;

    @NotBlank(message = "La direccion es obligatoria")
    private String direccion;

    @Pattern(regexp = "\\d{10}", message = "El teléfono debe tener 10 dígitos")
    private String telefono;

    @PastOrPresent(message = "La fecha de registro debe ser valida")
    private Date fecRegistro;

}
