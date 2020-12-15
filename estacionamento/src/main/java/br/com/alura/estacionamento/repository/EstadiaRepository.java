package br.com.alura.estacionamento.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.estacionamento.modelo.Estadia;

@Repository
public interface EstadiaRepository extends JpaRepository<Estadia, Long> {

	
	  @Query(value =
	  " SELECT * FROM Estadia es, Veiculo ve WHERE ve.id = es.veiculo_id AND es.saida_hora IS NULL and es.veiculo_id = :veiculo_id"
	  , nativeQuery = true) Estadia findEstadiaAbertaByVeicId(@Param("veiculo_id")
	  Long veiculo_id);
	  
	 
		
		  @Query(value =
		  " SELECT sum(valor_total) FROM Estadia es, Veiculo ve, Nota no WHERE ve.id = es.veiculo_id AND es.id = no.estadia_id AND es.saida_hora IS NOT NULL and es.veiculo_id = :veiculo_id"
		  , nativeQuery = true) int findEstadiaFechadaByVeicId(@Param("veiculo_id")
		  Long veiculo_id);
		 
	
	//List<Estadia> findByVeiculo_IdAndEntradaIsNull(Long id);

}
