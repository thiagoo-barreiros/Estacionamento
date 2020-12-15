package br.com.alura.estacionamento.modelo;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Estadia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Veiculo veiculo;

	private LocalDateTime entradaHora = LocalDateTime.now();

	private LocalDateTime saidaHora;
	
	public Estadia () {
		
	}

	public Estadia(Veiculo veiculo, LocalDateTime horaEntrada) {

		this.veiculo = veiculo;
		this.entradaHora = horaEntrada;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public LocalDateTime getEntradaHora() {
		return entradaHora;
	}

	public void setEntradaHora(LocalDateTime entradaHora) {
		this.entradaHora = entradaHora;
	}

	public LocalDateTime getSaidaHora() {
		return saidaHora;
	}

	public Estadia setSaidaHora(LocalDateTime saidaHora) {
		this.saidaHora = saidaHora;
		return this;
	}

}