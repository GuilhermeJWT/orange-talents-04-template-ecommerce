package br.com.zupacademy.guilhermesantos.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.guilhermesantos.mercadolivre.model.ModelUsuario;

@Repository
public interface UsuarioRepository extends JpaRepository<ModelUsuario, Long>{

}
