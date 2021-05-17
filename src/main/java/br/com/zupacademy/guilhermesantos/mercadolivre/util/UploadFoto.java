package br.com.zupacademy.guilhermesantos.mercadolivre.util;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFoto {

	public Set<String> salvaFotos(List<MultipartFile> imagens);

}
