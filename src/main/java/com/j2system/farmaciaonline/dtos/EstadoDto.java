package com.j2system.farmaciaonline.dtos;

public class EstadoDto {

	private Integer id;
	private String nome;

	public EstadoDto() {
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
		return "EstadoDto [id=" + id + ", nome=" + nome + "]";
	}
	
}
