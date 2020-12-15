package br.com.alura.estacionamento.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.estacionamento.modelo.Estadia;
import br.com.alura.estacionamento.modelo.Nota;
import br.com.alura.estacionamento.modelo.Veiculo;
import br.com.alura.estacionamento.repository.EstadiaRepository;
import br.com.alura.estacionamento.repository.NotaRepository;
import br.com.alura.estacionamento.repository.VeiculoRepository;

@Service
public class EstacionamentoService {

	private final EstadiaRepository estadiaRepository;

	@Autowired
	private VeiculoRepository veiculoRepository;

	private final NotaRepository notaRepository;

	public EstacionamentoService(EstadiaRepository estadiaRepository, VeiculoRepository veiculoRepository,
			NotaRepository notaRepository) {
		this.estadiaRepository = estadiaRepository;
		this.veiculoRepository = veiculoRepository;
		this.notaRepository = notaRepository;
	}

	public String entradaEstadia(Veiculo veiculo) {

		Optional<Veiculo> existeVeiculo = veiculoRepository.findByPlaca(veiculo.getPlaca());
		Estadia estadia;

		if (existeVeiculo.isEmpty()) {
			Veiculo veiculo2 = veiculoRepository.save(veiculo);
			estadia = new Estadia(veiculo2, LocalDateTime.now());
		} else {
			estadia = new Estadia(existeVeiculo.get(), LocalDateTime.now());
		}
		estadiaRepository.save(estadia);
		String valores = "A primeira 1 hora R$ 5,00 e a cada hora posterior R$ 2,00";
		return valores;

	}

	public Float calculaValorTotal(Estadia estadia) {

		float valorEstadia = 5;
		long horasPermanecidas = ChronoUnit.HOURS.between(estadia.getEntradaHora(), estadia.getSaidaHora());
		if (horasPermanecidas > 1) {
			valorEstadia += horasPermanecidas * 2;
		}
		return valorEstadia;
	}

	public Nota fecharEstadia(String placa) {

		System.out.println(placa);

		Optional<Veiculo> veiculo = veiculoRepository.findByPlaca(placa);
		Nota nota = new Nota();

		if (veiculo.isEmpty()) {
			System.out.println("Veiculo não encontrado");
		} else {
			Estadia estadia = estadiaRepository.findEstadiaAbertaByVeicId(veiculo.get().getId());
			Estadia estadiaFechada = estadiaRepository.save(estadia.setSaidaHora(LocalDateTime.now()));
			nota.setEstadia(estadiaFechada);
			nota.setValorTotal(calculaValorTotal(estadiaFechada));
			notaRepository.save(nota);
		}
		// System.out.println("Valor Total Estadia : " + nota.getValorTotal());

		return nota;
	}

	public String relatorioPorPlaca(String placa) {
		Optional<Veiculo> veiculo = veiculoRepository.findByPlaca(placa);
		int x = 0;

		if (veiculo.isEmpty()) {
			System.out.println("Veiculo não encontrado");
		} else {
			x = estadiaRepository.findEstadiaFechadaByVeicId(veiculo.get().getId());

		}

		String a = "O valor Total de Todas As Estadias para o Veiculo com a Placa: " + veiculo.get().getPlaca()
				+ " É de: " + x;

		return a;
	}

}
