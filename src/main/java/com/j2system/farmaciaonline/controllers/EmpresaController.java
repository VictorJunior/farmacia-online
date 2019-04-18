package com.j2system.farmaciaonline.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.j2system.farmaciaonline.dtos.EmpresaDto;
import com.j2system.farmaciaonline.entities.Cidade;
import com.j2system.farmaciaonline.entities.Empresa;
import com.j2system.farmaciaonline.response.Response;
import com.j2system.farmaciaonline.services.EmpresaService;

@RestController
@RequestMapping("/empresas")
@CrossOrigin
public class EmpresaController {

	private static final Logger log = LoggerFactory.getLogger(EmpresaController.class);
	
	@Autowired
	private EmpresaService empresaService;
	
	public EmpresaController() {
	}
	
	/**
	 * Retorna uma empresa dado um ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<EmpresaDto>>
	 */	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Response<EmpresaDto>> buscarPorId(@PathVariable("id") Integer id) {
		log.info("Buscando empresa por ID: {}", id);
		Response<EmpresaDto> response = new Response<EmpresaDto>();
		Optional<Empresa> empresa = empresaService.buscarPorId(id);

		if (!empresa.isPresent()) {
			log.info("Empresa não encontrada para o ID: {}", id);
			response.getErrors().add("Empresa não encontrada para o ID " + id);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.converterEmpresaDto(empresa.get()));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Retorna uma empresa dado um CNPJ.
	 * 
	 * @param cnpj
	 * @return ResponseEntity<Response<EmpresaDto>>
	 */
	@RequestMapping(value="/cnpj/{cnpj}", method=RequestMethod.GET)
	public ResponseEntity<Response<EmpresaDto>> buscarPorCnpj(@PathVariable("cnpj") String cnpj) {
		log.info("Buscando empresa por CNPJ: {}", cnpj);
		Response<EmpresaDto> response = new Response<EmpresaDto>();
		Optional<Empresa> empresa = empresaService.buscarPorCnpj(cnpj);

		if (!empresa.isPresent()) {
			log.info("Empresa não encontrada para o CNPJ: {}", cnpj);
			response.getErrors().add("Empresa não encontrada para o CNPJ " + cnpj);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.converterEmpresaDto(empresa.get()));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Retorna uma lista de empresas dado uma Razão Social
	 * 
	 * @param razaoSocial
	 * @return ResponseEntity<Response<List<EmpresaDto>>>
	 */		
	@RequestMapping(value="/razaoSocial/{razaoSocial}", method=RequestMethod.GET)
	public ResponseEntity<Response<List<EmpresaDto>>> buscarPorRazaoSocial(@PathVariable("razaoSocial") String razaoSocial) {
		log.info("Buscando empresas por Razão Social: {}", razaoSocial);
		
		Response<List<EmpresaDto>> response = new Response<List<EmpresaDto>>();
		
		List<Empresa> empresas = empresaService.buscarPorRazaoSocial(razaoSocial);
		
		if (empresas.isEmpty()) {
			log.info("Nenhuma empresa selecionada pelo Razão Social: {}", razaoSocial);
			response.getErrors().add("Nenhuma empresa selecionada pela Razão Social " + razaoSocial);
			return ResponseEntity.badRequest().body(response);
		}
		
		List<EmpresaDto> listDto = new ArrayList<>();
		empresas.stream().forEach(emp -> listDto.add(converterEmpresaDto(emp)));
				
		response.setData(listDto);
		
		return ResponseEntity.ok().body(response);		
	}
	
	/**
	 * Retorna uma lista de empresas dado um Nome Fantasia
	 * 
	 * @param nomeFantasia
	 * @return ResponseEntity<Response<List<EmpresaDto>>>
	 */	
	@RequestMapping(value="/nomeFantasia/{nomeFantasia}", method=RequestMethod.GET)
	public ResponseEntity<Response<List<EmpresaDto>>> buscarPorNomeFantasia(@PathVariable("nomeFantasia") String nomeFantasia) {
		log.info("Buscando empresas por Nome Fantasia: {}", nomeFantasia);
		
		Response<List<EmpresaDto>> response = new Response<List<EmpresaDto>>();
		
		List<Empresa> empresas = empresaService.buscarPorNomeFantasia(nomeFantasia);
		
		if (empresas.isEmpty()) {
			log.info("Nenhuma empresa selecionada pelo Nome Fantasia: {}", nomeFantasia);
			response.getErrors().add("Nenhuma empresa selecionada pelo Nome Fantasia " + nomeFantasia);
			return ResponseEntity.badRequest().body(response);
		}
		
		List<EmpresaDto> listDto = new ArrayList<>();
		empresas.stream().forEach(emp -> listDto.add(converterEmpresaDto(emp)));
				
		response.setData(listDto);
		
		return ResponseEntity.ok().body(response);		
	}
	
	/**
	 * Retorna uma lista com todas as empresas
	 * 
	 * @return ResponseEntity<Response<List<EmpresaDto>>>
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<Response<List<EmpresaDto>>> buscarTodasEmpresas() {
		log.info("Buscando todas as empresas");
		
		Response<List<EmpresaDto>> response = new Response<List<EmpresaDto>>();
		
		List<Empresa> empresas = empresaService.buscarTodasEmpresas();
		
		if (empresas.isEmpty()) {
			log.info("Nenhuma empresa cadastrada");
			response.getErrors().add("Nenhuma empresa cadastrada");
			return ResponseEntity.badRequest().body(response);
		}
		
		List<EmpresaDto> listDto = new ArrayList<>();
		empresas.stream().forEach(emp -> listDto.add(converterEmpresaDto(emp)));
				
		response.setData(listDto);
		
		return ResponseEntity.ok().body(response);		
	}
	
	/**
	 * Adiciona uma nova Empresa.
	 * 
	 * @param empresa
	 * @param result
	 * @return ResponseEntity<Response<EmpresaDto>>
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Response<EmpresaDto>> adicionar(@Valid @RequestBody EmpresaDto empresaDto,
			BindingResult result) {
		log.info("Adicionando empresa: {}", empresaDto.toString());
		Response<EmpresaDto> response = new Response<EmpresaDto>();
		
		Empresa empresa = this.converterDtoParaEmpresa(empresaDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando empresa: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		empresa = this.empresaService.persistir(empresa);
		response.setData(this.converterEmpresaDto(empresa));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Atualiza os dados de uma empresa.
	 * 
	 * @param id
	 * @param empresaDto
	 * @return ResponseEntity<Response<Empresa>>
	 */	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Response<EmpresaDto>> atualizar(@PathVariable("id") Integer id,
			@Valid @RequestBody EmpresaDto empresaDto, BindingResult result) {
		log.info("Atualizando empresa: {}", empresaDto.toString());
		Response<EmpresaDto> response = new Response<EmpresaDto>();

		empresaDto.setId(Optional.of(id));
		Empresa empresa = this.converterDtoParaEmpresa(empresaDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando lançamento: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		empresa = this.empresaService.persistir(empresa);
		response.setData(this.converterEmpresaDto(empresa));
		return ResponseEntity.ok(response);
	}	
	
	/**
	 * Remove uma empresa por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<Empresa>>
	 */	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Integer id) {
		log.info("Removendo empresa: {}", id);
		Response<String> response = new Response<String>();
		Optional<Empresa> empresa = this.empresaService.buscarPorId(id);

		if (!empresa.isPresent()) {
			log.info("Erro ao remover devido a empresa ID: {} ser inválida.", id);
			response.getErrors().add("Erro ao remover empresa. Empresa não encontrada para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		this.empresaService.remover(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	/**
	 * Popula um DTO com os dados de uma empresa.
	 * 
	 * @param empresa
	 * @return EmpresaDto
	 */
	private EmpresaDto converterEmpresaDto(Empresa empresa) {
		EmpresaDto empresaDto = new EmpresaDto();
		empresaDto.setId(Optional.of(empresa.getId()));
		empresaDto.setBairro(empresa.getBairro());
		empresaDto.setCep(empresa.getCep());
		empresaDto.setCidadeId(empresa.getCidade().getId());
		empresaDto.setCnpj(empresa.getCnpj());
		empresaDto.setComplemento(Optional.ofNullable(empresa.getComplemento()));
		empresaDto.setEndereco(empresa.getEndereco());
		empresaDto.setInscricaoEstadual(Optional.ofNullable(empresa.getInscricaoEstadual()));
		empresaDto.setLatitude(Optional.ofNullable(empresa.getLatitude()));
		empresaDto.setLongitude(Optional.ofNullable(empresa.getLongitude()));
		empresaDto.setNomeFantasia(empresa.getNomeFantasia());
		empresaDto.setNumero(empresa.getNumero());
		empresaDto.setRazaoSocial(empresa.getRazaoSocial());

		return empresaDto;
	}
	
	/**
	 * Converte uma EmpresaDto para uma entidade Empresa.
	 * 
	 * @param empresaDto
	 * @param result
	 * @return Empresa 
	 */
	private Empresa converterDtoParaEmpresa(EmpresaDto empresaDto, BindingResult result) {
		Empresa empresa = new Empresa();

		if (empresaDto.getId().isPresent()) {
			Optional<Empresa> emp = this.empresaService.buscarPorId(empresaDto.getId().get());
			
			if (emp.isPresent()) {
				empresa = emp.get();
			} 
			else {
				result.addError(new ObjectError("empresa", "Empresa não encontrada."));
			}
		} 
		
		empresa.setCidade(new Cidade());		
		empresa.getCidade().setId(empresaDto.getCidadeId());
				
		empresa.setBairro(empresaDto.getBairro());
		empresa.setCep(empresaDto.getCep());
		empresa.setCnpj(empresaDto.getCnpj());
		empresa.setComplemento(empresaDto.getComplemento().isPresent() ? empresaDto.getComplemento().get() : null);
		empresa.setEndereco(empresaDto.getEndereco());
		empresa.setInscricaoEstadual(empresaDto.getInscricaoEstadual().isPresent() ? empresaDto.getInscricaoEstadual().get() : null);
		empresa.setLatitude(empresaDto.getLatitude().isPresent() ? empresaDto.getLatitude().get() : null);
		empresa.setLongitude(empresaDto.getLongitude().isPresent() ? empresaDto.getLongitude().get() : null);
		empresa.setNomeFantasia(empresaDto.getNomeFantasia());
		empresa.setNumero(empresaDto.getNumero());
		empresa.setRazaoSocial(empresaDto.getRazaoSocial());
		
		return empresa;
	}
	
}
