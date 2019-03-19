package com.j2system.farmaciaonline.services;

import java.util.Optional;

import com.j2system.farmaciaonline.entities.Cliente;

public interface ClienteService {

	/**
	 * Busca e retorna um cliente dado um email.
	 * 
	 * @param email
	 * @return Optional<Cliente>
	 */
	Optional<Cliente> buscarPorEmail(String email);
	
	/**
	 * Busca e retorna um cliente por ID.
	 * 
	 * @param id
	 * @return Optional<Funcionario>
	 */
	Optional<Cliente> buscarPorId(Integer id);
	
	/**
	 * Persiste um cliente na base de dados.
	 * 
	 * @param cliente
	 * @return Cliente
	 */
	Cliente persistir(Cliente cliente);
	
	/**
	 * Remove um cliente da base de dados.
	 * 
	 * @param id
	 */
	void remover(Integer id);
	
}
