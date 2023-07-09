package com.api.estacionamento.controllers;

import com.api.estacionamento.exceptions.VagaIndisponivelException;
import com.api.estacionamento.models.Carro;
import com.api.estacionamento.models.Estacionamento;
import com.api.estacionamento.services.EstacionamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estacionamento/carro")
public class EstacionamentoController {

    private EstacionamentoService estacionamentoService;

    public EstacionamentoController(EstacionamentoService estacionamentoService) {
        this.estacionamentoService = estacionamentoService;
    }

    @GetMapping
    public ResponseEntity<List<Estacionamento>> listarCarrosEstacionados() {
        List<Estacionamento> carrosEstacionados = estacionamentoService.listarCarrosEstacionados();
        return ResponseEntity.ok().body(carrosEstacionados);
    }

    @PostMapping
    public ResponseEntity<Estacionamento> cadastrarCarroNoEstacionamento(@Valid @RequestBody Carro carro, @RequestParam(required = false, defaultValue = "0") int numeroVaga) {
        Estacionamento estacionamento = estacionamentoService.cadastrarCarroNoEstacionamento(carro, numeroVaga);
        return ResponseEntity.status(HttpStatus.CREATED).body(estacionamento);
    }


    @PostMapping("/entrada/{id}")
    public ResponseEntity<Estacionamento> registrarEntrada(@Valid @PathVariable Long id) {
        Estacionamento estacionamento = estacionamentoService.registrarEntrada(id);
        return ResponseEntity.ok().body(estacionamento);
    }

    @PostMapping("/saida/{id}")
    public ResponseEntity<Estacionamento> registrarSaida(@Valid @PathVariable Long id) {
        Estacionamento estacionamento = estacionamentoService.registrarSaida(id);
        return ResponseEntity.ok().body(estacionamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estacionamento> atualizarCarroEstacionado(@PathVariable Long id, @RequestBody Carro carro) {
        Estacionamento estacionamentoAtualizado = estacionamentoService.atualizarCarroEstacionado(id, carro);
        return ResponseEntity.ok().body(estacionamentoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCarroEstacionado(@PathVariable Long id) {
        estacionamentoService.excluirCarroEstacionado(id);
        return ResponseEntity.noContent().build();
    }
}







