package com.j2system.farmaciaonline.dtos;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

public class PedidoDto {

	private Integer id;
	
	@NotEmpty(message = "Data não pode ser vazia.")
	private Date data;
	
	private String tipoEntrega;	
	private Long clienteId;	
	private Long enderecoId;	
	private Long empresaId;	
	public PedidoDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTipoEntrega() {
		return tipoEntrega;
	}

	public void setTipoEntrega(String tipoEntrega) {
		this.tipoEntrega = tipoEntrega;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(Long enderecoId) {
		this.enderecoId = enderecoId;
	}

	public Long getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}

	@Override
	public String toString() {
		return "PedidoDto [id=" + id + ", data=" + data + ", tipoEntrega=" + tipoEntrega + ", clienteId=" + clienteId
				+ ", enderecoId=" + enderecoId + ", empresaId=" + empresaId + "]";
	}
	
}
