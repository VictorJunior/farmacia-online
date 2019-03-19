package com.j2system.farmaciaonline.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.j2system.farmaciaonline.entities.Cliente;
import com.j2system.farmaciaonline.entities.Empresa;
import com.j2system.farmaciaonline.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
	@Transactional(readOnly=true)
	List<Pedido> findByCliente(Cliente cliente);
	
	@Transactional(readOnly=true)
	Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
	
	@Transactional(readOnly=true)
	List<Pedido> findByEmpresa(Empresa empresa);
	
	@Transactional(readOnly=true)
	Page<Pedido> findByEmpresa(Empresa empresa, Pageable pageRequest);

}
