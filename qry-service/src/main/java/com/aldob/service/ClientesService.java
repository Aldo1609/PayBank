package com.aldob.service;

import com.aldob.dto.ClientesEvent;
import com.aldob.model.Clientes;

public interface ClientesService {
    Clientes getDatosClienteById(Long idCliente);

    void processClientesEvents(ClientesEvent clientesEvent);

}