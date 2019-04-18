package com.j2system.farmaciaonline.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.j2system.farmaciaonline.dtos.CidadeDto;
import com.j2system.farmaciaonline.dtos.EstadoDto;
import com.j2system.farmaciaonline.entities.Cidade;
import com.j2system.farmaciaonline.entities.Estado;
import com.j2system.farmaciaonline.response.Response;
import com.j2system.farmaciaonline.services.CidadeService;
import com.j2system.farmaciaonline.services.EstadoService;

@RestController
@RequestMapping("/estados")
@CrossOrigin
public class EstadoController {

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private CidadeService cidadeService;

	public EstadoController() {
	}
	
	/**
	 * Retorna uma lista com todas os estados
	 * 
	 * @return ResponseEntity<Response<List<EstadoDto>>>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Response<List<EstadoDto>>> buscarEstados() {
		Response<List<EstadoDto>> response = new Response<List<EstadoDto>>();
		
		List<Estado> estados = estadoService.buscarEstados();
		
		if (estados.isEmpty()) {
			response.getErrors().add("Nenhum estado cadastrado");
			return ResponseEntity.badRequest().body(response);
		}
		
		List<EstadoDto> listDto = new ArrayList<>();
		estados.stream().forEach(estado -> listDto.add(converterEstadoDto(estado)));
		
		response.setData(listDto);
		
		return ResponseEntity.ok().body(response);
	}

	/**
	 * Retorna uma lista com todas as cidadades de um estado
	 * 
	 * @param estadoId
	 * @return ResponseEntity<Response<List<CidadeDto>>>
	 */
	@RequestMapping(value = "/{estadoId}/cidades", method = RequestMethod.GET)
	public ResponseEntity<Response<List<CidadeDto>>> findCidades(@PathVariable Integer estadoId) {
		Response<List<CidadeDto>> response = new Response<List<CidadeDto>>();		
		
		List<Cidade> cidades = cidadeService.buscarCidadesPorEstado(estadoId);
		
		if (cidades.isEmpty()) {
			response.getErrors().add("Nenhum cidade cadastrada para este Estado");
			return ResponseEntity.badRequest().body(response);
		}
		
		List<CidadeDto> listDto = new ArrayList<>();
		cidades.stream().forEach(cidade -> listDto.add(converterCidadeDto(cidade)));
		
		response.setData(listDto);
		
		return ResponseEntity.ok().body(response);
	}
	
	/**
	 * Popula um DTO com os dados de um estado.
	 * 
	 * @param estado
	 * @return EstadoDto
	 */
	private EstadoDto converterEstadoDto(Estado estado) {
		EstadoDto estadoDto = new EstadoDto();
		estadoDto.setId(estado.getId());
		estadoDto.setNome(estado.getNome());
		
		return estadoDto;
	}
	
	/**
	 * Popula um DTO com os dados de uma cidade.
	 * 
	 * @param cidade
	 * @return CidadeDto
	 */
	private CidadeDto converterCidadeDto(Cidade cidade) {
		CidadeDto cidadeDto = new CidadeDto();
		cidadeDto.setId(cidade.getId());
		cidadeDto.setNome(cidade.getNome());
		
		return cidadeDto;
	}
	
}
