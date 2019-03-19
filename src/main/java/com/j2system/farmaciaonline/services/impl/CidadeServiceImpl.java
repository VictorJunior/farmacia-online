package com.j2system.farmaciaonline.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j2system.farmaciaonline.entities.Cidade;
import com.j2system.farmaciaonline.repositories.CidadeRepository;
import com.j2system.farmaciaonline.services.CidadeService;

@Service
public class CidadeServiceImpl implements CidadeService{

	private static final Logger log = LoggerFactory.getLogger(CidadeServiceImpl.class);
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Override
	public List<Cidade> buscarCidadesPorEstado(Integer estadoId) {
		log.info("Buscando cidades de uma UF {}", estadoId);
		return cidadeRepository.findCidades(estadoId);
	}

}
