package com.j2system.farmaciaonline.services;

import java.util.List;

import com.j2system.farmaciaonline.entities.Estado;

public interface EstadoService {
	/**
	 * Retorna os Estados.
	 * 
	 * 
	 * @return List<Estado>
	 */
	List<Estado> buscarEstados();
}
