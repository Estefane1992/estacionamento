package com.api.estacionamento.repositores;


import com.api.estacionamento.models.Carro;
import com.api.estacionamento.models.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {
    Estacionamento findByCarroAndDataHoraSaidaIsNull (Carro carro);



    List<Estacionamento> findByDataHoraSaidaIsNull();
}
