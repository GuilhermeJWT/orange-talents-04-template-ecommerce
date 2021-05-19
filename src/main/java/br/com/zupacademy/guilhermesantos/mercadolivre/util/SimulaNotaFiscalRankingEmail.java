package br.com.zupacademy.guilhermesantos.mercadolivre.util;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelCompra;

@Service
public class SimulaNotaFiscalRankingEmail {
	
	RestTemplate templateClient = new RestTemplate();
	
	public void processaEnviaNotaFiscal(ModelCompra modelCompra) {
		Map<String, Object> verifica = Map.of("idCompra", modelCompra.getId(), "idComprador", modelCompra.getUsuarioComprador().getId());
	
		templateClient.postForEntity("http://localhost:8080/fechamentocompra/notafiscal", verifica, String.class);
		
	}

	public void processaRanking(ModelCompra modelCompra) {
		Map<String, Object> verifica = Map.of("idCompra", modelCompra.getId(), "idDonoProduto", modelCompra.getUsuarioComprador().getId());
		
		templateClient.postForEntity("http://localhost:8080/fechamentocompra/rankingvendedor", verifica, String.class);
	}

}
