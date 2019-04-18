package com.j2system.farmaciaonline.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j2system.farmaciaonline.entities.Cliente;
import com.j2system.farmaciaonline.repositories.ClienteRepository;
import com.j2system.farmaciaonline.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public Optional<Cliente> buscarPorEmail(String email) {
		log.info("Buscando um Cliente por Email {}", email);
		return clienteRepository.findByEmail(email);
	}

	@Override
	public Optional<Cliente> buscarPorId(Integer id) {
		log.info("Buscando um Cliente pelo ID {}", id);
		return clienteRepository.findById(id);
	}

	@Override
	public Cliente persistir(Cliente cliente) {
		log.info("Persistindo cliente: {}", cliente);
		return this.clienteRepository.save(cliente);
	}

	@Override
	public void remover(Integer id) {
		log.info("Removendo o cliente ID {}", id);
		this.clienteRepository.deleteById(id);
	}

}
