package com.j2system.farmaciaonline.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.j2system.farmaciaonline.entities.Produto;
import com.j2system.farmaciaonline.repositories.ProdutoRepository;
import com.j2system.farmaciaonline.services.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public Page<Produto> buscarPorNome(Integer empresaId, String nome, PageRequest pageRequest) {
		return produtoRepository.findProdutosPorEmpresaNome(empresaId, nome, pageRequest);
	}

	@Override
	public Page<Produto> buscarTodosProdutos(Integer empresaId, PageRequest pageRequest) {
		return produtoRepository.findProdutosPorEmpresa(empresaId, pageRequest);
	}

	@Override
	public Optional<Produto> buscarPorCodigoEmpresa(Integer empresaId, String codigoNaEmpresa) {
		return produtoRepository.findProdutosPorCodigoNaEmpresa(empresaId, codigoNaEmpresa);
	}

	@Override
	public Optional<Produto> buscarPorId(Integer id) {
		return produtoRepository.findById(id);
	}

	@Override
	public Produto persistir(Produto produto) {
		return this.produtoRepository.save(produto);
	}

	@Override
	public void remover(Integer id) {
		this.produtoRepository.deleteById(id);
	}

}
