package com.aldob.service;

import com.aldob.dto.ClientesDTO;
import com.aldob.model.Clientes;

public interface ClientesService {

    ClientesDTO addCliente(ClientesDTO clientes);

    ClientesDTO updateCliente(Long idCliente, ClientesDTO clientes);

    String deleteClient(Long idCliente);

}
