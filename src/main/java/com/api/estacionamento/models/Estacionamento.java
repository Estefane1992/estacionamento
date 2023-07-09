package com.api.estacionamento.models;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
public class Estacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 70)
    private int numeroVaga;

    @Column(nullable = false, length = 70)
    private LocalDateTime dataHoraEntrada;

    @Column(length = 70)
    private LocalDateTime dataHoraSaida;

    @ManyToOne
    private Carro carro;

    public Estacionamento() {
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroVaga() {
        return numeroVaga;
    }

    public void setNumeroVaga(int numeroVaga) {
        this.numeroVaga = numeroVaga;
    }

    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public LocalDateTime getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    // Métodos adicionais

    public void registrarEntrada(Carro carro) {
        LocalDateTime now = LocalDateTime.now();
        this.carro = carro;
        this.dataHoraEntrada = now;
        this.dataHoraSaida = null;
    }

    public void registrarSaida() {
        LocalDateTime now = LocalDateTime.now();
        this.dataHoraSaida = now;
    }

    public double calcularValorEstacionamento(double valorPorHora) {
        if (dataHoraEntrada != null && dataHoraSaida != null) {
            Duration duracao = Duration.between(dataHoraEntrada, dataHoraSaida);
            long horas = duracao.toHours();
            return horas * valorPorHora;
        } else {
            return 0.0;
        }
    }
}
