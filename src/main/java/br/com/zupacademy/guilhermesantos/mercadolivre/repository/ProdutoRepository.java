package br.com.zupacademy.guilhermesantos.mercadolivre.repository;

import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelProdutos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ModelProdutos, Long> {

}
