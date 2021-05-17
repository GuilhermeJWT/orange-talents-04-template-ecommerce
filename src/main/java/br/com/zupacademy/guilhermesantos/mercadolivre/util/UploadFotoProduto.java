package br.com.zupacademy.guilhermesantos.mercadolivre.util;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Primary
public class UploadFotoProduto implements UploadFoto{

	   public Set<String> salvaFotos(List<MultipartFile> imagens) {
	        return imagens.stream()
	                .map(i -> "http://bucket.io/"+
	                i.getOriginalFilename())
	                .collect(Collectors.toSet());
	    }
	
}
