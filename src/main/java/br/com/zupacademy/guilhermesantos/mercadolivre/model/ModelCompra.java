package br.com.zupacademy.guilhermesantos.mercadolivre.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.guilhermesantos.mercadolivre.enums.PagamentoGateway;

@Entity
@Table(name = "compra")
public class ModelCompra implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "A Quantidade deve ser Informada!")
	@Positive(message = "A Quantidade deve ser maior que 0!")
	private int quantidade;
	
	@ManyToOne(optional = false)
	@NotNull(message = "O Produto deve ser Informado!")
	@Valid
	private ModelProdutos produtoComprado;
	
	@ManyToOne(optional = false)
	@NotNull(message = "O Usuário deve ser Informado!")
	@Valid
	private ModelUsuario usuarioComprador;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O Modo de Pagamento deve ser Informado!")
	private PagamentoGateway pagamentoGateway;
	
	public ModelCompra(ModelProdutos produtoComprado, int quantidade, ModelUsuario usuarioComprador, PagamentoGateway pagamentoGateway) {
		this.produtoComprado = produtoComprado;
		this.quantidade = quantidade;
		this.usuarioComprador = usuarioComprador;
		this.pagamentoGateway = pagamentoGateway;
	}
	
	public Long getId() {
		return id;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public ModelProdutos getProdutoComprado() {
		return produtoComprado;
	}
	
	public ModelUsuario getUsuarioComprador() {
		return usuarioComprador;
	}
	
	public PagamentoGateway getPagamentoGateway() {
		return pagamentoGateway;
	}
	
}
