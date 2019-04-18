package com.j2system.farmaciaonline.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false)
	private String codigoBarras;
	
	@Column(nullable = false)
	private String nome;
	
	private String descricao;
	private String marca;
	private String fabricante;
	private String registroMS;
	private String principioAtivo;
	private String dosagem;
	private String quantidade;
	private Date dataCriacao;
	private Date dataAtualizacao;
	
	@OneToMany(mappedBy = "id.produto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();
	
	@OneToMany(mappedBy = "id.produto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ProdutoEmpresa> produtosEmpresa = new ArrayList<>();
	
	public Produto() {
	}

	public Produto(Integer id, String codigoBarras, String nome, String descricao, String marca,
			String fabricante, String registroMS, String principioAtivo, String dosagem, String quantidade) {
		super();
		this.id = id;
		this.codigoBarras = codigoBarras;
		this.nome = nome;
		this.descricao = descricao;
		this.marca = marca;
		this.fabricante = fabricante;
		this.registroMS = registroMS;
		this.principioAtivo = principioAtivo;
		this.dosagem = dosagem;
		this.quantidade = quantidade;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getRegistroMS() {
		return registroMS;
	}

	public void setRegistroMS(String registroMS) {
		this.registroMS = registroMS;
	}

	public String getPrincipioAtivo() {
		return principioAtivo;
	}

	public void setPrincipioAtivo(String principioAtivo) {
		this.principioAtivo = principioAtivo;
	}

	public String getDosagem() {
		return dosagem;
	}

	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public List<ProdutoEmpresa> getProdutosEmpresa() {
		return produtosEmpresa;
	}

	public void setProdutosEmpresa(List<ProdutoEmpresa> produtosEmpresa) {
		this.produtosEmpresa = produtosEmpresa;
	}

	@PreUpdate
    public void preUpdate() {
        dataAtualizacao = new Date();
    }
     
    @PrePersist
    public void prePersist() {
        final Date atual = new Date();
        dataCriacao = atual;
        dataAtualizacao = atual;
    }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
