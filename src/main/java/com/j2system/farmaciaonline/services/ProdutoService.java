package com.j2system.farmaciaonline.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.j2system.farmaciaonline.entities.Produto;

public interface ProdutoService {

	/**
	 * Retorna uma listagem de produtos dado um Nome.
	 * 
	 * @param empresaId
	 * @param nome
	 * @return List<Produto>
	 */
	Page<Produto> buscarPorNome(Integer empresaId, String nomeFantasia, PageRequest pageRequest);
	
	/**
	 * Retorna uma listagem de todas os produtos de uma empresa.
	 *
	 * @pagam empresaId
	 * @return List<Produto>
	 */
	Page<Produto> buscarTodosProdutos(Integer empresaId, PageRequest pageRequest);
	
	/**
	 * Retorna um produto por Codigo Na Empresa.
	 *
	 * @pagam empresaId
	 * @param codigoNaEmpresa
	 * @return Optional<Produto>
	 */
	Optional<Produto> buscarPorCodigoEmpresa(Integer empresaId, String codigoNaEmpresa);
	
	/**
	 * Retorna um produto por ID.
	 * 
	 * @param id
	 * @return Optional<Produto>
	 */
	Optional<Produto> buscarPorId(Integer id);
	
	/**
	 * Cadastra um novo produto na base de dados.
	 * 
	 * @param produto
	 * @return Produto
	 */
	Produto persistir(Produto produto);
	
	/**
	 * Remove um produto da base de dados.
	 * 
	 * @param id
	 */
	void remover(Integer id);
	
}
