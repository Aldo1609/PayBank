package com.aldob.controller;

import com.aldob.dto.CuentasDTO;
import com.aldob.service.CuentasService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CuentasController {

    private final CuentasService cuentasService;

    @PutMapping(path = "/actualizarCuenta/{idCuenta}")
    public CuentasDTO updateCuenta(@PathVariable Long idCuenta, @RequestBody CuentasDTO cuentasDTO){
        return cuentasService.updateCuenta(idCuenta, cuentasDTO);
    }

    @DeleteMapping(path = "/bajaCuenta/{idCuenta}")
    public String bajaCuenta(@PathVariable Long idCuenta){
        return cuentasService.deleteCuenta(idCuenta);
    }

}
