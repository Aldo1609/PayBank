package com.aldob.repository;

import com.aldob.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {
}
