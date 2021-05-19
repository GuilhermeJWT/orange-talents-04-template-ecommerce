package br.com.zupacademy.guilhermesantos.mercadolivre.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.zupacademy.guilhermesantos.mercadolivre.enums.PagamentoGateway;
import br.com.zupacademy.guilhermesantos.mercadolivre.util.ModelRetornoGatewayPagamento;

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

	@OneToMany(mappedBy = "modelCompra", cascade = CascadeType.MERGE, orphanRemoval = true)
	private Set<ModelTransacao> transacoes = new HashSet<>();
	
	public ModelCompra(ModelProdutos produtoComprado, int quantidade, ModelUsuario usuarioComprador, PagamentoGateway pagamentoGateway) {
		this.produtoComprado = produtoComprado;
		this.quantidade = quantidade;
		this.usuarioComprador = usuarioComprador;
		this.pagamentoGateway = pagamentoGateway;
	}
	
	public void processaPagamentoCompra(@Valid ModelRetornoGatewayPagamento retornoPagamentoGateway) {
		ModelTransacao modelTransacao = retornoPagamentoGateway.toTransacao(this);
		
		Assert.isTrue(!this.transacoes.contains(modelTransacao), "OPS! já Existe uma Trasação semelhante a essa!" + modelTransacao);
		Set<ModelTransacao> transacoesSucesso = this.transacoes.stream().filter(ModelTransacao :: concluidaComSucesso).collect(Collectors.toSet());
		
		Assert.isTrue(transacoesSucesso.isEmpty(), "Essa Compra já foi Concluida com Suceso!");
		
		this.transacoes.add(modelTransacao);
	}
	
	public boolean processadaComSucesso() {
		return transacoesConluidasComSucesso().isEmpty();
	}
	
	private Set<ModelTransacao> transacoesConluidasComSucesso(){
		Set<ModelTransacao> transacoesSucesso = this.transacoes.stream().filter(ModelTransacao :: concluidaComSucesso).collect(Collectors.toSet());
		
		Assert.isTrue(transacoesSucesso.size() <= 1, "OPS! Mais de uma Transação Conluida nessa Compra!");
		
		return transacoesSucesso;
	}

	@Deprecated
	public ModelCompra() {
		
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
		ModelCompra other = (ModelCompra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
