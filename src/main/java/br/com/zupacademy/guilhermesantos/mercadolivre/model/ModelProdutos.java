package br.com.zupacademy.guilhermesantos.mercadolivre.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    public ModelProdutos(String nome, int quantidade, String descricao, BigDecimal valor, ModelCategoria idCategoria){
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.idCategoria = idCategoria;
    }

    @Deprecated
    public ModelProdutos(){

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

}
