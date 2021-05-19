package br.com.zupacademy.guilhermesantos.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.guilhermesantos.mercadolivre.dto.ModelCompraDTO;
import br.com.zupacademy.guilhermesantos.mercadolivre.dto.ModelPagSeguroDTO;
import br.com.zupacademy.guilhermesantos.mercadolivre.dto.ModelPayPalDTO;
import br.com.zupacademy.guilhermesantos.mercadolivre.enums.PagamentoGateway;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelCompra;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelProdutos;
import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelUsuario;
import br.com.zupacademy.guilhermesantos.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.guilhermesantos.mercadolivre.util.CalculaProcessamentoCompra;

@RestController
@RequestMapping(value = "/fechamentocompra")
public class FechamentoCompraController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CalculaProcessamentoCompra calculaProcessamentoCompra;

	@PostMapping(value = "/salva")
	@Transactional
	public String finalizaCompra(@RequestBody @Valid ModelCompraDTO modelCompraDTO, UriComponentsBuilder builder)
			throws BindException {

		ModelProdutos produtoDaVenda = manager.find(ModelProdutos.class, modelCompraDTO.getId());
		boolean verificou = produtoDaVenda.baixaEstoque(modelCompraDTO.getQuantidade());
		int quantidade = modelCompraDTO.getQuantidade();

		if (verificou) {
			ModelUsuario usuarioComprador = usuarioRepository.findByLogin("guitestezup@gmail.com").get();
			PagamentoGateway pagamentoGateway = modelCompraDTO.getPagamento();
			ModelCompra compra = new ModelCompra(produtoDaVenda, quantidade, usuarioComprador,
					modelCompraDTO.getPagamento());
			manager.persist(compra);

			if (pagamentoGateway.equals(PagamentoGateway.PAGSEGURO)) {
				String urlRetornoPagseguro = builder.path("retorno-pagseguro/{id}").buildAndExpand(compra.getId())
						.toString();
				return "pagseguro.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPagseguro;
			} else if (pagamentoGateway.equals(PagamentoGateway.PAYPAL)){
				String urlRetornoPaypal = builder.path("retorno-paypal/{id}").buildAndExpand(compra.getId()).toString();
				return "paypal.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPaypal;
			}

		}

		BindException exception = new BindException(modelCompraDTO, "modelCompraDTO");
		exception.reject(null, "Compra Não Autorizada, Devido ao Estoque!");

		throw exception;

	}
	
	@PostMapping(value = "/fechamento2/retorno-pagseguro/{id}")
	@Transactional
	public String salvaFechamento2PagSeguro(@PathVariable("id") Long idCompra,@RequestBody @Valid ModelPagSeguroDTO modelPagSeguroDTO) {
		return calculaProcessamentoCompra.processaPagamentos(idCompra, modelPagSeguroDTO);
	}
	
	@PostMapping(value = "/fechamento2/retorno-paypal/{id}")
	@Transactional
	public String salvaFechamento2PayPal(@PathVariable("id") Long idCompra,@RequestBody @Valid ModelPayPalDTO modelPayPalDTO) {
		return calculaProcessamentoCompra.processaPagamentos(idCompra, modelPayPalDTO);
	}
	
	/*Simula uma Api de Nota fiscal, Tipo TECNOSPEED, SEFAZ, retorno meio parecido*/
	@PostMapping(value = "/notafiscal")
	@Transactional
	public void simulaApiNotaFiscal(@RequestBody Long idCompra, Long idComprador) {
		JOptionPane.showMessageDialog(null, "Nota Criada com Sucesso para Compra: " + idCompra + " Comprador: " + idComprador);
	}
	
	/*Simula um envio para Api Ranking*/
	@PostMapping(value = "/rankingvendedor")
	@Transactional
	public void simulaApiRanking(@RequestBody Long idCompra, Long idVendedor) {
		JOptionPane.showMessageDialog(null, "Sucesso Procesamento Rank");
	}
	
}
