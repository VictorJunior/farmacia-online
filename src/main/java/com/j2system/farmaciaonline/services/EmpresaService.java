package com.j2system.farmaciaonline.services;

import java.util.List;
import java.util.Optional;

import com.j2system.farmaciaonline.entities.Empresa;

public interface EmpresaService {
	/**
	 * Retorna uma empresa dado um CNPJ.
	 * 
	 * @param cnpj
	 * @return Optional<Empresa>
	 */
	Optional<Empresa> buscarPorCnpj(String cnpj);
	
	/**
	 * Retorna uma listagem de empresas dado uma Razão Social.
	 * 
	 * @param razaoSocial
	 * @return List<Empresa>
	 */
	List<Empresa> buscarPorRazaoSocial(String razaoSocial);
	
	/**
	 * Retorna uma listagem de empresas dado um Nome Fantasia.
	 * 
	 * @param nomeFantasia
	 * @return List<Empresa>
	 */
	List<Empresa> buscarPorNomeFantasia(String nomeFantasia);
	
	/**
	 * Retorna uma listagem de todas as empresas.
	 *
	 * @return List<Empresa>
	 */
	List<Empresa> buscarTodasEmpresas();
	
	/**
	 * Retorna uma empresa por ID.
	 * 
	 * @param id
	 * @return Optional<Empresa>
	 */
	Optional<Empresa> buscarPorId(Integer id);
	
	/**
	 * Cadastra uma nova empresa na base de dados.
	 * 
	 * @param empresa
	 * @return Empresa
	 */
	Empresa persistir(Empresa empresa);
	
	/**
	 * Remove uma empresa da base de dados.
	 * 
	 * @param id
	 */
	void remover(Integer id);
	
}
