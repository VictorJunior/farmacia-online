package com.j2system.farmaciaonline.services;

import java.util.List;

import com.j2system.farmaciaonline.entities.Cidade;

public interface CidadeService {
	/**
	 * Retorna as cidades de um Estado.
	 * 
	 * @param estadoId
	 * @return List<Cidade>
	 */
	List<Cidade> buscarCidadesPorEstado(Integer estadoId);
}
