package com.example.estacionamento.repositories;

import com.example.estacionamento.entidades.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface VagaRepository extends JpaRepository<Vaga, UUID> {
    @Query(value = "SELECT * FROM tb_vaga WHERE ocupada = true", nativeQuery = true)
    List<Vaga> encontrarVagasOcupadas();
}
