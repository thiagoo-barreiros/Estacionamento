package br.com.alura.estacionamento.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.estacionamento.modelo.Nota;
import br.com.alura.estacionamento.modelo.Veiculo;
import br.com.alura.estacionamento.service.EstacionamentoService;

@RestController
@RequestMapping("/estacionamento")
public class EstadiaController {
	
	private final EstacionamentoService estacionamentoService;
	
	public EstadiaController(EstacionamentoService estacionamentoService){
		this.estacionamentoService = estacionamentoService;
	}
	

    @PostMapping("/estacionar")
    public ResponseEntity<String> estacionar(@RequestBody Veiculo veiculo) {
    	
    	String result = estacionamentoService.entradaEstadia(veiculo);
        return ResponseEntity.ok()
            .body(result);
    }
    
    @PutMapping("/fechar/{placa}")
    public ResponseEntity<Nota> fechar (@PathVariable String placa) {
    	
    	Nota result = estacionamentoService.fecharEstadia(placa);
    	return ResponseEntity.ok()
    			.body(result);
    			
    }
    
    @GetMapping("/relatorio/{placa}")
    public ResponseEntity<String> relatorio (@PathVariable String placa){
    	
    	
    	String result = estacionamentoService.relatorioPorPlaca(placa);
    	return ResponseEntity.ok()
    			.body(result);
    }
		
	
	
}
