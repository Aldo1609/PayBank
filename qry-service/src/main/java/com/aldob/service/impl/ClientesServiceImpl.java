package com.aldob.service.impl;

import com.aldob.dto.ClientesEvent;
import com.aldob.model.Clientes;
import com.aldob.repository.ClientesRepository;
import com.aldob.service.ClientesService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientesServiceImpl implements ClientesService {

    private final ClientesRepository clientesRepository;

    @Override
    public Clientes getDatosClienteById(Long idCliente){
        return clientesRepository.findByIdCliente(idCliente);
    }

    @Override
    @KafkaListener(topics = "client-event-topic", groupId = "client-event-group")
    public void processClientesEvents(ClientesEvent clientesEvent){
        Clientes clientes = clientesEvent.getClientes();
        if (clientesEvent.getEventType().equals("CreateCliente")){
            clientesRepository.save(clientes);
        }
        if (clientesEvent.getEventType().equals("UpdateCliente")){
            Clientes existingClient = clientesRepository.findById(clientes.getIdCliente()).orElse(null);
            if (existingClient != null){
                existingClient.setNombre(clientes.getNombre());
                existingClient.setApellido(clientes.getApellido());
                existingClient.setGenero(clientes.getGenero());
                existingClient.setDireccion(clientes.getDireccion());
                existingClient.setTelefono(clientes.getTelefono());
                existingClient.setFecRegistro(clientes.getFecRegistro());
                clientesRepository.save(existingClient);
            }
        }
        if (clientesEvent.getEventType().equals("DeleteCliente")){
            clientesRepository.deleteById(clientes.getIdCliente());
        }
    }
}
