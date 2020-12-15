package br.com.alura.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.estacionamento.modelo.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long>{

}
