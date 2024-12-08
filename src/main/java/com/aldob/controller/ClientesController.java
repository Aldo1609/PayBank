package com.aldob.controller;

import com.aldob.dto.ClientesDTO;
import com.aldob.service.ClientesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ClientesController {

    private final ClientesService clientesService;

    @PostMapping(path = "/nuevoCliente")
    public ClientesDTO nuevoCliente(@RequestBody ClientesDTO clientesDTO){
        return clientesService.addCliente(clientesDTO);
    }

    @PutMapping(path = "/actualizarCliente/{idCliente}")
    public ClientesDTO updateCliente(@PathVariable Long idCliente, @RequestBody ClientesDTO clientesDTO){
        return clientesService.updateCliente(idCliente, clientesDTO);
    }

    @DeleteMapping(path = "/bajaCliente/{idCliente}")
    public String deleteClient(@PathVariable Long idCliente){
        return clientesService.deleteClient(idCliente);
    }
}
