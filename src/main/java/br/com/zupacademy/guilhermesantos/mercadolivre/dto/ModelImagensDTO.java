 package br.com.zupacademy.guilhermesantos.mercadolivre.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ModelImagensDTO {
	
	@NotNull(message = "A Imagem deve ser Inforamda!")
	@Size(min = 1, message = "Deve cadastrar pelomenos 1 Imagem!")
	private List<MultipartFile> fotosProdutos = new ArrayList<>();
	
	public List<MultipartFile> getFotosProdutos() {
		return fotosProdutos;
	}
	
	public void setFotosProdutos(List<MultipartFile> fotosProdutos) {
		this.fotosProdutos = fotosProdutos;
	}

}
