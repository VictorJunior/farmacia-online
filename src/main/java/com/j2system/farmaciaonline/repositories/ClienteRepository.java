package com.j2system.farmaciaonline.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.j2system.farmaciaonline.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	@Transactional(readOnly=true)
	Optional<Cliente> findByEmail(String email);

}
