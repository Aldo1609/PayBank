package com.aldob.service;

import com.aldob.dto.CuentasDTO;

public interface CuentasService {

    CuentasDTO updateCuenta(Long idCuenta, CuentasDTO cuentasDTO);

    String deleteCuenta(Long idCuenta);

}
