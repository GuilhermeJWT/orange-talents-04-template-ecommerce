package br.com.zupacademy.guilhermesantos.mercadolivre.dto;

import br.com.zupacademy.guilhermesantos.mercadolivre.anotation.GenericValidExistId;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelCategoria;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelProdutos;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ModelProdutosDTO {

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

    @GenericValidExistId(domainClass = ModelCategoria.class, fieldName = "id", message = "Categoria não Encontrada, Informe Outra!")
    @NotNull(message = "A Categoria deve ser Informada!")
    private Long idCategoria;

    public ModelProdutos converte(EntityManager manager){
        ModelCategoria categoriaId = manager.find(ModelCategoria.class, idCategoria);
        return new ModelProdutos(nome, quantidade, descricao, valor, categoriaId);
    }

    public ModelProdutosDTO(String nome, int quantidade, String descricao, BigDecimal valor, Long idCategoria){
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.idCategoria = idCategoria;
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

    public Long getIdCategoria() {
        return idCategoria;
    }
}
