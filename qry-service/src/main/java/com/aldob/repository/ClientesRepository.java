package com.aldob.repository;

import com.aldob.model.Clientes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends CrudRepository<Clientes, Long> {

    Clientes findByIdCliente(Long idCliente);


}
