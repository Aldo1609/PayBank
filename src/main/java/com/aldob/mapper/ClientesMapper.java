package com.aldob.mapper;

import com.aldob.dto.ClientesDTO;
import com.aldob.model.Clientes;
import org.springframework.stereotype.Component;

@Component
public class ClientesMapper {

    public ClientesDTO toDTO(Clientes clientes){
        ClientesDTO dto = new ClientesDTO();
        dto.setIdCliente(clientes.getIdCliente());
        dto.setNombre(clientes.getNombre());
        dto.setApellido(clientes.getApellido());
        dto.setGenero(clientes.getGenero());
        dto.setDireccion(clientes.getDireccion());
        dto.setTelefono(clientes.getTelefono());
        dto.setFecRegistro(clientes.getFecRegistro());
        return dto;
    }

}
