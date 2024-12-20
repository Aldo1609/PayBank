package com.aldob.service;

import com.aldob.dto.CuentasEvent;
import com.aldob.model.Cuentas;

public interface CuentasService {

    Cuentas getDatosCuentaById(Long idCuenta);

    void processCuentasEvents(CuentasEvent cuentasEvent);

}
