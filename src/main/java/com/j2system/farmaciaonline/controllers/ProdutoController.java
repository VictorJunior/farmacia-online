package com.j2system.farmaciaonline.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.j2system.farmaciaonline.dtos.ProdutoDto;
import com.j2system.farmaciaonline.entities.Empresa;
import com.j2system.farmaciaonline.entities.Produto;
import com.j2system.farmaciaonline.response.Response;
import com.j2system.farmaciaonline.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
@CrossOrigin
public class ProdutoController {
	
	@Autowired
	ProdutoService produtoService;
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;
	
	public ProdutoController() {
	}
	
	/**
	 * Retorna a listagem de produtos por nome.
	 * 
	 * @param empresaId
	 * @param nome
	 * @return ResponseEntity<Response<Page<ProdutoDto>>>
	 */
	@GetMapping(value = "/{empresaId}/nome/{nome}")
	public ResponseEntity<Response<Page<ProdutoDto>>> buscarPorNome(
			@PathVariable("empresaId") Integer empresaId,
			@PathVariable("nome") String nome,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "ASC") String dir) {		
		Response<Page<ProdutoDto>> response = new Response<Page<ProdutoDto>>();

		PageRequest pageRequest = PageRequest.of(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		Page<Produto> produtos = produtoService.buscarPorNome(empresaId, nome, pageRequest);
		
		if (produtos.isEmpty()) {
			response.getErrors().add("Nenhum produto cadastrado para esta Empresa.");
			return ResponseEntity.badRequest().body(response);
		}
		
		Page<ProdutoDto> produtosDto = produtos.map(produto -> this.converterProdutoDto(produto));

		response.setData(produtosDto);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Retorna a listagem de produtos.
	 * 
	 * @param empresaId
	 * @return ResponseEntity<Response<Page<ProdutoDto>>>
	 */
	@GetMapping(value = "/{empresaId}")
	public ResponseEntity<Response<Page<ProdutoDto>>> buscarTodosProdutos(
			@PathVariable("empresaId") Integer empresaId,			
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "ASC") String dir) {		
		Response<Page<ProdutoDto>> response = new Response<Page<ProdutoDto>>();

		PageRequest pageRequest = PageRequest.of(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		Page<Produto> produtos = produtoService.buscarTodosProdutos(empresaId, pageRequest);
		
		if (produtos.isEmpty()) {
			response.getErrors().add("Nenhum produto cadastrado para esta Empresa.");
			return ResponseEntity.badRequest().body(response);
		}
		
		Page<ProdutoDto> produtosDto = produtos.map(produto -> this.converterProdutoDto(produto));

		response.setData(produtosDto);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Retorna produto por codigo na empresa.
	 * 
	 * @param empresaId
	 * @param codigoNaEmpresa
	 * @return ResponseEntity<Response<Page<ProdutoDto>>>
	 */
	@GetMapping(value = "/{empresaId}/codigoNaEmpresa/{codigoNaEmpresa}")
	public ResponseEntity<Response<ProdutoDto>> buscarPorCodigoEmpresa(
			@PathVariable("empresaId") Integer empresaId, @PathVariable("codigoNaEmpresa") String codigoNaEmpresa) {		
		Response<ProdutoDto> response = new Response<ProdutoDto>();
		
		Optional<Produto> produto = produtoService.buscarPorCodigoEmpresa(empresaId, codigoNaEmpresa);
		
		if (!produto.isPresent()) {
			response.getErrors().add("Produto não encontrado para esta Empresa.");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(converterProdutoDto(produto.get()));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Retorna produto por id.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<Page<ProdutoDto>>>
	 */
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Response<ProdutoDto>> buscarPorId(@PathVariable("id") Integer id) {		
		Response<ProdutoDto> response = new Response<ProdutoDto>();
		
		Optional<Produto> produto = produtoService.buscarPorId(id);
		
		if (!produto.isPresent()) {
			response.getErrors().add("Produto não encontrado para esta Empresa.");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(converterProdutoDto(produto.get()));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Adiciona um novo Produto.
	 * 
	 * @param produto
	 * @param result
	 * @return ResponseEntity<Response<ProdutoDto>>
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Response<ProdutoDto>> adicionar(@Valid @RequestBody ProdutoDto produtoDto,
			BindingResult result) {
		Response<ProdutoDto> response = new Response<ProdutoDto>();
		
		Produto produto = converterDtoParaProduto(produtoDto, result);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		produto = produtoService.persistir(produto);
		response.setData(this.converterProdutoDto(produto));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Atualiza os dados de um produto.
	 * 
	 * @param id
	 * @param produtoDto
	 * @return ResponseEntity<Response<Produto>>
	 */	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Response<ProdutoDto>> atualizar(@PathVariable("id") Integer id,
			@Valid @RequestBody ProdutoDto produtoDto, BindingResult result) {
		Response<ProdutoDto> response = new Response<ProdutoDto>();

		produtoDto.setId(Optional.of(id));
		Produto produto = this.converterDtoParaProduto(produtoDto, result);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		produto = produtoService.persistir(produto);
		response.setData(converterProdutoDto(produto));
		return ResponseEntity.ok(response);
	}	
	
	/**
	 * Popula um DTO com os dados de um produto.
	 * 
	 * @param produto
	 * @return ProdutoDto
	 */
	private ProdutoDto converterProdutoDto(Produto produto) {
		ProdutoDto produtoDto = new ProdutoDto();
		produtoDto.setId(Optional.of(produto.getId()));
		produtoDto.setCodProdutoEmpresa(produto.getCodProdutoEmpresa());
		produtoDto.setDescricao(Optional.ofNullable(produto.getDescricao()));
		produtoDto.setDosagem(Optional.ofNullable(produto.getDosagem()));
		produtoDto.setFabricante(Optional.ofNullable(produto.getFabricante()));
		produtoDto.setMarca(Optional.ofNullable(produto.getMarca()));
		produtoDto.setNome(produto.getNome());
		produtoDto.setPreco(produto.getPreco());
		produtoDto.setPrincipioAtivo(Optional.ofNullable(produto.getPrincipioAtivo()));
		produtoDto.setQuantidade(Optional.ofNullable(produto.getQuantidade()));
		produtoDto.setRegistroMS(Optional.ofNullable(produto.getRegistroMS()));
		produtoDto.setEmpresaId(produto.getEmpresa().getId());
		
		return produtoDto;
	}
	
	/**
	 * Converte uma ProdutoDto para uma entidade Produto.
	 * 
	 * @param produtoDto
	 * @param result
	 * @return Produto 
	 */
	private Produto converterDtoParaProduto(ProdutoDto produtoDto, BindingResult result) {
		Produto produto = new Produto();

		if (produtoDto.getId().isPresent()) {
			Optional<Produto> prod = produtoService.buscarPorId(produtoDto.getId().get());
			
			if (prod.isPresent()) {
				produto = prod.get();
			} 
			else {
				result.addError(new ObjectError("produto", "Produto não encontrado."));
			}
		} 
		
		produto.setEmpresa(new Empresa());		
		produto.getEmpresa().setId(produtoDto.getEmpresaId());
				
		produto.setCodProdutoEmpresa(produtoDto.getCodProdutoEmpresa());
		produto.setDescricao(produtoDto.getDescricao().isPresent() ? produtoDto.getDescricao().get() : null);
		produto.setDosagem(produtoDto.getDosagem().isPresent() ? produtoDto.getDosagem().get() : null);
		produto.setFabricante(produtoDto.getFabricante().isPresent() ? produtoDto.getFabricante().get() : null);
		produto.setMarca(produtoDto.getMarca().isPresent() ? produtoDto.getMarca().get() : null);
		produto.setNome(produtoDto.getNome());
		produto.setPreco(produtoDto.getPreco());
		produto.setPrincipioAtivo(produtoDto.getPrincipioAtivo().isPresent() ? produtoDto.getPrincipioAtivo().get() : null);
		produto.setQuantidade(produtoDto.getQuantidade().isPresent() ? produtoDto.getQuantidade().get() : null);
		produto.setRegistroMS(produtoDto.getRegistroMS().isPresent() ? produtoDto.getRegistroMS().get() : null);
				
		return produto;
	}
	
}
