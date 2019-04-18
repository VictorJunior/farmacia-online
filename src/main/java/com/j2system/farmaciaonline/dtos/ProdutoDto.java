package com.j2system.farmaciaonline.dtos;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class ProdutoDto {

	private Optional<Integer> id = Optional.empty();
	
	@NotEmpty(message = "Código Produto Empresa não pode ser vazio.")
	private String codProdutoEmpresa;
	
	@NotEmpty(message = "Nome não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
	private String nome;
	
	private Double preco;
	
	private Optional<String> descricao = Optional.empty();
	private Optional<String> marca = Optional.empty();
	private Optional<String> fabricante = Optional.empty();
	private Optional<String> registroMS = Optional.empty();
	private Optional<String> principioAtivo = Optional.empty();
	private Optional<String> dosagem = Optional.empty();
	private Optional<String> quantidade = Optional.empty();
	
	private Integer empresaId;
	
	public ProdutoDto() {
	}

	public Optional<Integer> getId() {
		return id;
	}

	public void setId(Optional<Integer> id) {
		this.id = id;
	}

	public String getCodProdutoEmpresa() {
		return codProdutoEmpresa;
	}

	public void setCodProdutoEmpresa(String codProdutoEmpresa) {
		this.codProdutoEmpresa = codProdutoEmpresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Optional<String> getDescricao() {
		return descricao;
	}

	public void setDescricao(Optional<String> descricao) {
		this.descricao = descricao;
	}

	public Optional<String> getMarca() {
		return marca;
	}

	public void setMarca(Optional<String> marca) {
		this.marca = marca;
	}

	public Optional<String> getFabricante() {
		return fabricante;
	}

	public void setFabricante(Optional<String> fabricante) {
		this.fabricante = fabricante;
	}

	public Optional<String> getRegistroMS() {
		return registroMS;
	}

	public void setRegistroMS(Optional<String> registroMS) {
		this.registroMS = registroMS;
	}

	public Optional<String> getPrincipioAtivo() {
		return principioAtivo;
	}

	public void setPrincipioAtivo(Optional<String> principioAtivo) {
		this.principioAtivo = principioAtivo;
	}

	public Optional<String> getDosagem() {
		return dosagem;
	}

	public void setDosagem(Optional<String> dosagem) {
		this.dosagem = dosagem;
	}

	public Optional<String> getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Optional<String> quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Integer empresaId) {
		this.empresaId = empresaId;
	}

	@Override
	public String toString() {
		return "ProdutoDto [id=" + id + ", codProdutoEmpresa=" + codProdutoEmpresa + ", nome=" + nome + ", preco="
				+ preco + ", descricao=" + descricao + ", marca=" + marca + ", fabricante=" + fabricante
				+ ", registroMS=" + registroMS + ", principioAtivo=" + principioAtivo + ", dosagem=" + dosagem
				+ ", quantidade=" + quantidade + ", empresaId=" + empresaId + "]";
	}
	
}
