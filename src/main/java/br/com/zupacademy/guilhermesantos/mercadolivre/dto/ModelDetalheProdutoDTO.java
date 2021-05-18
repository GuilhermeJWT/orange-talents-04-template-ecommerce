package br.com.zupacademy.guilhermesantos.mercadolivre.dto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.IntStream;

import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelDetalheCaracteristica;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelProdutos;

public class ModelDetalheProdutoDTO {

	private String descricao;
	private String nome;
	private BigDecimal valor;
	private Set<ModelDetalheCaracteristica> caracteristica;
	private Set<String> linksImagens;
	private Set<String> perguntas;
	private Set<Map<String, String>> opinioes;
	private double mediaNotas;

	public ModelDetalheProdutoDTO(ModelProdutos modelProdutos) {
		this.descricao = modelProdutos.getDescricao();
		this.nome = modelProdutos.getNome();
		this.valor = modelProdutos.getValor();
		this.caracteristica = modelProdutos.mapCaracteristicas(ModelDetalheCaracteristica:: new);
		this.linksImagens = modelProdutos.mapImagens(fotos -> fotos.getLink());
		this.perguntas = modelProdutos.mapPerguntas(pergunta -> pergunta.getTitulo());
		this.opinioes = modelProdutos.mapOpinioes(opiniao -> {
			return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
		});
		
		Set<Integer> notas = modelProdutos.mapOpinioes(opiniao -> opiniao.getNota());
		IntStream mapToInt =  notas.stream().mapToInt(nota -> nota);
		OptionalDouble average =  mapToInt.average();
		
		if(average.isPresent()) {
			this.mediaNotas = average.getAsDouble();
		}
		
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}
	
	public Set<ModelDetalheCaracteristica> getCaracteristica() {
		return caracteristica;
	}
	
	public Set<String> getLinksImagens() {
		return linksImagens;
	}
	
	public Set<String> getPerguntas() {
		return perguntas;
	}
	
	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}
	
	public double getMediaNotas() {
		return mediaNotas;
	}
}
