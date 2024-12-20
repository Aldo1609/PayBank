package com.aldob.repository;

import com.aldob.model.Cuentas;
import org.springframework.data.repository.CrudRepository;

public interface CuentasRepository extends CrudRepository<Cuentas, Long> {

    Cuentas findByIdCuenta(Long idCuenta);

}
