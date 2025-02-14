package com.example.estacionamento.repositories;

import com.example.estacionamento.entidades.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarroRepository extends JpaRepository<Carro, UUID> {
    Carro findCarroByVagaId(UUID id);
}
