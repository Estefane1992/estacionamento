package com.api.estacionamento.services;
import com.api.estacionamento.exceptions.NotFoundException;
import com.api.estacionamento.models.Carro;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import com.api.estacionamento.repositores.CarroRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CarroService {
    private CarroRepository carroRepository;


    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public Carro findById(Long id) {
        Optional<Carro> optionalCarro = carroRepository.findById(id);
        return optionalCarro.orElseThrow(() -> new NotFoundException("Carro não encontrado com ID: " + id));
    }
    public List<Carro> findAllCarro() {
        List<Carro> carros = carroRepository.findAll();
        if (carros.isEmpty()) {
            throw new NotFoundException("Não existem carros cadastrados");
        }
        return carros;

    }


    //    cadastrar carro no estacionamento na vaga disponível
    public Carro cadastrarCarro(Carro carro) {
        return carroRepository.save(carro);

    }

    // atualizar as informações de um carro estacionado

    public Carro atualizarCarro(Long id, Carro novoCarro) {
        Carro carroExistente = findById(id);
        carroExistente.setPlaca(novoCarro.getPlaca());
        carroExistente.setModelo(novoCarro.getModelo());
        carroExistente.setCor(novoCarro.getCor());
        carroExistente.setAno(novoCarro.getAno());

        return carroRepository.save(carroExistente);

    }

    //    excluir um carro do estacionamento quando for retirado
    //    pedi ajuda no chat para melhorar a msg de erro
    public void excluirCarro(Long id) {
        findById(id);
        try {
            this.carroRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("Não é possível excluir o carro. Carro não encontrado com ID: " + id);
        }

    }

}
