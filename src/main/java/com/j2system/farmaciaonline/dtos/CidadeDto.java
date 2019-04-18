package com.j2system.farmaciaonline.dtos;

public class CidadeDto {
	
	private Integer id;
	private String nome;

	public CidadeDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "CidadeDto [id=" + id + ", nome=" + nome + "]";
	}
	
}
