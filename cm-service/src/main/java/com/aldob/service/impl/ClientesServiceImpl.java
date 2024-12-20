package com.aldob.service.impl;

import com.aldob.dto.ClientesDTO;
import com.aldob.dto.ClientesEvent;
import com.aldob.dto.CuentasDTO;
import com.aldob.dto.CuentasEvent;
import com.aldob.exceptions.ClientesException;
import com.aldob.mapper.ClientesMapper;
import com.aldob.mapper.CuentasMapper;
import com.aldob.model.Clientes;
import com.aldob.model.Cuentas;
import com.aldob.repository.ClientesRepository;
import com.aldob.repository.CuentasRepository;
import com.aldob.service.ClientesService;
import lombok.AllArgsConstructor;
import org.hibernate.cfg.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.Random;

import java.util.Date;

@Service
@AllArgsConstructor
public class ClientesServiceImpl implements ClientesService {

    private final ClientesRepository clientesRepository;
    private final CuentasRepository cuentasRepository;
    private final CuentasMapper cuentasMapper;
    private final ClientesMapper clientesMapper;
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Override
    public ClientesDTO addCliente(ClientesDTO clientesDTO) {
        try {
            Random randomNumbers = new Random();
            Clientes clientes = new Clientes();
            clientes.setNombre(clientesDTO.getNombre());
            clientes.setApellido(clientesDTO.getApellido());
            clientes.setGenero(clientesDTO.getGenero());
            clientes.setDireccion(clientesDTO.getDireccion());
            clientes.setTelefono(clientesDTO.getTelefono());
            clientes.setFecRegistro(new Date());
            Clientes savedCliente = clientesRepository.save(clientes);
            ClientesDTO savedClienteDTO = clientesMapper.toDTO(savedCliente);

            Cuentas cuentas = new Cuentas();
            cuentas.setClientes(savedCliente);
            cuentas.setSaldo(0L);
            cuentas.setDeuda(0L);
            cuentas.setFechaCorte(savedCliente.getFecRegistro());
            cuentas.setLimiteCredito(1000 + randomNumbers.nextLong(9001));
            cuentas.setActivo(true);
            Cuentas savedCuentas = cuentasRepository.save(cuentas);
            CuentasDTO savedCuentasDTO = cuentasMapper.toDTO(savedCuentas);

            kafkaTemplate.send("client-event-topic", new ClientesEvent("CreateCliente", savedClienteDTO));
            kafkaTemplate.send("account-event-topic", new CuentasEvent("CreateCuenta", savedCuentasDTO));

            return savedClienteDTO;
        } catch (Exception e) {
            throw new ClientesException("Error in addCliente: " + e.getMessage());
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
