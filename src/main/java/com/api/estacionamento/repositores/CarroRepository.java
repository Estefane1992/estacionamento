package com.api.estacionamento.repositores;

import com.api.estacionamento.models.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}
