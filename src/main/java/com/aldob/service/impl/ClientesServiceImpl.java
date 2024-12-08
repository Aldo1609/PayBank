package com.aldob.service.impl;

import com.aldob.dto.ClientesDTO;
import com.aldob.dto.ClientesEvent;
import com.aldob.exceptions.ClientesException;
import com.aldob.mapper.ClientesMapper;
import com.aldob.model.Clientes;
import com.aldob.repository.ClientesRepository;
import com.aldob.service.ClientesService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientesServiceImpl implements ClientesService {

    private final ClientesRepository clientesRepository;
    private final ClientesMapper clientesMapper;
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Override
    public ClientesDTO addCliente(ClientesDTO clientesDTO) {
        try {
            Clientes clientes = new Clientes();
            clientes.setNombre(clientesDTO.getNombre());
            clientes.setApellido(clientesDTO.getApellido());
            clientes.setGenero(clientesDTO.getGenero());
            clientes.setDireccion(clientesDTO.getDireccion());
            clientes.setTelefono(clientesDTO.getTelefono());
            clientes.setFecRegistro(clientesDTO.getFecRegistro());
            ClientesDTO savedClienteDTO = clientesMapper.toDTO(clientesRepository.save(clientes));
            kafkaTemplate.send("client-event-topic", new ClientesEvent("CreateCliente", savedClienteDTO));
            return savedClienteDTO;
        }catch (Exception e){
            throw new ClientesException("Error in addCliente:" + e.getMessage());
        }
    }

    @Override
    public ClientesDTO updateCliente(Long idCliente, ClientesDTO clientesDTO) {
        try {
            return clientesRepository.findById(idCliente).map(
                    clientes -> {
                        clientes.setNombre(clientesDTO.getNombre());
                        clientes.setApellido(clientesDTO.getApellido());
                        clientes.setGenero(clientesDTO.getGenero());
                        clientes.setDireccion(clientesDTO.getDireccion());
                        clientes.setTelefono(clientesDTO.getTelefono());
                        clientes.setFecRegistro(clientesDTO.getFecRegistro());
                        ClientesDTO savedClienteDTO =  clientesMapper.toDTO(clientesRepository.save(clientes));
                        kafkaTemplate.send("client-event-topic", new ClientesEvent("UpdateCliente", savedClienteDTO));
                        return savedClienteDTO;
                    }
            ).orElseThrow(() -> new ClientesException("Client not found with id:" + idCliente) );
        }catch (Exception e){
            throw new ClientesException("Error in updateClient: " + e.getMessage());
        }
    }

    @Override
    public String deleteClient(Long idCliente) {
        try {
            if (clientesRepository.existsById(idCliente)){
                clientesRepository.deleteById(idCliente);
                kafkaTemplate.send("client-event-topic", new ClientesEvent("DeleteCliente", new ClientesDTO(idCliente)));
            }
            return "El cliente con id: " + idCliente + " fue dado de baja exitosamente!";
        }catch (Exception e){
            throw new ClientesException("Error in deleteCliente: " + e.getMessage());
        }
    }


}
