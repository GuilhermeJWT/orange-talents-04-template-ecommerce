package br.com.zupacademy.guilhermesantos.mercadolivre.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadFotoProduto {

	public List<String> salvaFotos(List<MultipartFile> fotosProdutos){
		return (List<String>) fotosProdutos.stream()
				.map(foto -> "http://bucket.io/"
		        + foto.getOriginalFilename()).collect(Collectors.toSet());
	}
	
}
