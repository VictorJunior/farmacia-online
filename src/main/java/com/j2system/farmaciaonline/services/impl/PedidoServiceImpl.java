package com.j2system.farmaciaonline.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.j2system.farmaciaonline.entities.Cliente;
import com.j2system.farmaciaonline.entities.Empresa;
import com.j2system.farmaciaonline.entities.Pedido;
import com.j2system.farmaciaonline.repositories.PedidoRepository;
import com.j2system.farmaciaonline.services.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {
	
	private static final Logger log = LoggerFactory.getLogger(PedidoServiceImpl.class);

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override
	public Page<Pedido> buscarPorCliente(Cliente cliente, PageRequest pageRequest) {
		log.info("Buscando pedidos do cliente ID {}", cliente.getId());
		return this.pedidoRepository.findByCliente(cliente, pageRequest);
	}

	@Override
	public Page<Pedido> buscarPorEmpresa(Empresa empresa, PageRequest pageRequest) {
		log.info("Buscando pedidos da empresa ID{}", empresa.getId());
		return this.pedidoRepository.findByEmpresa(empresa, pageRequest);
	}

	@Override
	public Optional<Pedido> buscarPorId(Integer id) {
		log.info("Buscando um pedido pelo ID {}", id);
		return pedidoRepository.findById(id);
	}

	@Override
	public Pedido persistir(Pedido pedido) {
		log.info("Persistindo pedido: {}", pedido);
		return this.pedidoRepository.save(pedido);
	}

	@Override
	public void remover(Integer id) {
		log.info("Removendo o pedido ID {}", id);
		this.pedidoRepository.deleteById(id);
	}

}
