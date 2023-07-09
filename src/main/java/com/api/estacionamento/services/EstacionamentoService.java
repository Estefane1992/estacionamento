package com.api.estacionamento.services;

import com.api.estacionamento.exceptions.NotFoundException;
import com.api.estacionamento.exceptions.VagaIndisponivelException;
import com.api.estacionamento.models.Carro;
import com.api.estacionamento.models.Estacionamento;
import com.api.estacionamento.repositores.CarroRepository;
import com.api.estacionamento.repositores.EstacionamentoRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EstacionamentoService {

    private EstacionamentoRepository estacionamentoRepository;
    private CarroRepository carroRepository;

    public EstacionamentoService(EstacionamentoRepository estacionamentoRepository, CarroRepository carroRepository) {
        this.estacionamentoRepository = estacionamentoRepository;
        this.carroRepository = carroRepository;
    }


    public Estacionamento cadastrarCarroNoEstacionamento(Carro carro, Integer numeroVaga) {
        Estacionamento estacionamento = new Estacionamento();
        estacionamento.setCarro(carro);
        estacionamento.setDataHoraEntrada(LocalDateTime.now());

        if (numeroVaga != null) {
            estacionamento.setNumeroVaga(numeroVaga);
        }

        return estacionamentoRepository.save(estacionamento);
    }


    public Estacionamento registrarEntrada(Long id) {
        Estacionamento estacionamento = findById(id);
        estacionamento.setDataHoraEntrada(LocalDateTime.now());
        return estacionamentoRepository.save(estacionamento);
    }

    public Estacionamento registrarSaida(Long id) {
        Estacionamento estacionamento = findById(id);
        estacionamento.setDataHoraSaida(LocalDateTime.now());
        return estacionamentoRepository.save(estacionamento);
    }

    public List<Estacionamento> listarCarrosEstacionados() {
        return estacionamentoRepository.findByDataHoraSaidaIsNull();
    }

    public Estacionamento atualizarCarroEstacionado(Long id, Carro carro) {
        Estacionamento estacionamento = findById(id);
        estacionamento.setCarro(carro);
        return estacionamentoRepository.save(estacionamento);
    }

    public void excluirCarroEstacionado(Long id) {
        Estacionamento estacionamento = findById(id);
        estacionamentoRepository.delete(estacionamento);
    }

    private Estacionamento findById(Long id) {
        Optional<Estacionamento> optionalEstacionamento = estacionamentoRepository.findById(id);
        return optionalEstacionamento.orElseThrow(() -> new NotFoundException("Estacionamento não encontrado"));
    }
}
