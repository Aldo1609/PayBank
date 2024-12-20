package com.aldob.controller;

import com.aldob.model.Clientes;
import com.aldob.model.Cuentas;
import com.aldob.service.ClientesService;
import com.aldob.service.CuentasService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AppController {

    private final ClientesService clientesService;
    private final CuentasService cuentasService;

    @GetMapping(path = "/datosCliente/{idCliente}")
    public Clientes getDatosCliente(@PathVariable Long idCliente){
        return clientesService.getDatosClienteById(idCliente);
    }

    @GetMapping(path = "/datosCuenta/{idCuenta}")
    public Cuentas getDatosCuenta(@PathVariable Long idCuenta){
        return cuentasService.getDatosCuentaById(idCuenta);
    }
}