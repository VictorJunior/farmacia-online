package com.j2system.farmaciaonline.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j2system.farmaciaonline.entities.Estado;
import com.j2system.farmaciaonline.repositories.EstadoRepository;
import com.j2system.farmaciaonline.services.EstadoService;

@Service
public class EstadoServiceImpl implements EstadoService{

	private static final Logger log = LoggerFactory.getLogger(EstadoServiceImpl.class);
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Override
	public List<Estado> buscarEstados() {
		log.info("Buscando estados {}");
		return estadoRepository.findAllByOrderByNome();
	}

}
