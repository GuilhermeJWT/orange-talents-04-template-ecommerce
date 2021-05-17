package br.com.zupacademy.guilhermesantos.mercadolivre.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.zupacademy.guilhermesantos.mercadolivre.dto.ModelCaracteristicaDTO;

@Entity
@Table(name = "produto")
public class ModelProdutos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "O Nome deve ser informado!")
	private String nome;

	@NotNull(message = "A Quantidade deve ser Informada!")
	@Positive(message = "A Quantidade deve ser maior que 0!")
	private int quantidade;

	@NotEmpty(message = "A Descrição deve ser Informada!")
	@Size(max = 1000, message = "A Descrição deve ter no máximo 1000 Caracteres")
	private String descricao;

	@NotNull(message = "O Valor deve ser Informado!")
	@Positive(message = "O Valor deve ser maior que 0!")
	private BigDecimal valor;

	private LocalDateTime dataRegistro = LocalDateTime.now();

	@JsonIgnore
	@ManyToOne(optional = false)
	private ModelCategoria idCategoria;
	
	@ManyToOne
	private ModelUsuario usuario;

	@OneToMany(mappedBy = "modelProdutos", cascade = CascadeType.MERGE)
	private Set<ModelImagensProduto> fotos = new HashSet<>();
	
	public ModelProdutos(String nome, int quantidade, String descricao, BigDecimal valor, ModelCategoria idCategoria,
			List<ModelCaracteristicaDTO> caracteristicas) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.valor = valor;
		this.idCategoria = idCategoria;
	}

	@Deprecated
	public ModelProdutos() {

	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public ModelCategoria getIdCategoria() {
		return idCategoria;
	}
	
	public ModelUsuario getUsuario() {
		return usuario;
	}
	
	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public Set<ModelImagensProduto> getFotos() {
		return fotos;
	}

	public void salvaImagensProdutos(Set<String> links) {
		Set<ModelImagensProduto> fotos = links.stream().map(link -> new ModelImagensProduto(this, link))
				.collect(Collectors.toSet());
		this.fotos.addAll(fotos);
	}
	
	public boolean pertenceUsuario(ModelUsuario fotosProdutoUsuario) {
		return this.usuario.equals(fotosProdutoUsuario);
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
		ModelProdutos other = (ModelProdutos) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
