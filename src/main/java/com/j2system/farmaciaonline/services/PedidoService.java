package com.j2system.farmaciaonline.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.j2system.farmaciaonline.entities.Cliente;
import com.j2system.farmaciaonline.entities.Empresa;
import com.j2system.farmaciaonline.entities.Pedido;

public interface PedidoService {
	
	/**
	 * Retorna uma lista paginada de pedidos de um determinado cliente.
	 * 
	 * @param Cliente
	 * @param pageRequest
	 * @return Page<Pedido>
	 */
	Page<Pedido> buscarPorCliente(Cliente cliente, PageRequest pageRequest);
	
	/**
	 * Retorna uma lista paginada de pedidos de um determinada Empresa.
	 * 
	 * @param Empresas
	 * @param pageRequest
	 * @return Page<Pedido>
	 */
	Page<Pedido> buscarPorEmpresa(Empresa empresa, PageRequest pageRequest);
	
	/**
	 * Retorna um pedido por ID.
	 * 
	 * @param id
	 * @return Optional<Pedido>
	 */
	Optional<Pedido> buscarPorId(Integer id);
	
	/**
	 * Persiste um pedido na base de dados.
	 * 
	 * @param pedido
	 * @return Pedido
	 */
	Pedido persistir(Pedido pedido);
	
	/**
	 * Remove um pedido da base de dados.
	 * 
	 * @param id
	 */
	void remover(Integer id);
	
}
