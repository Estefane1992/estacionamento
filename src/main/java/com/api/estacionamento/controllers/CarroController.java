package com.api.estacionamento.controllers;

import com.api.estacionamento.models.Carro;
import com.api.estacionamento.services.CarroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    private CarroService carroService;

    @Autowired
    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> findById(@PathVariable Long id) {
        Carro carro = carroService.findById(id);
        if (carro != null) {
            return ResponseEntity.ok(carro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Carro>> findAllCarro() {
        List<Carro> carros = carroService.findAllCarro();
        return ResponseEntity.ok(carros);
    }

    @PostMapping
    public ResponseEntity<Carro> cadastrarCarro(@Valid @RequestBody Carro carro) {
        Carro novoCarro = carroService.cadastrarCarro(carro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCarro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizarCarro(@PathVariable Long id, @RequestBody Carro carro) {
        Carro carroExistente = carroService.atualizarCarro(id, carro);
        if (carroExistente != null) {
            return ResponseEntity.ok(carroExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirCarro(@PathVariable Long id) {
        this.carroService.excluirCarro(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Carro excluído com sucesso.");
    }
}
