package com.example.estacionamento.services;

import com.example.estacionamento.dtos.VagaRecordDto;
import com.example.estacionamento.entidades.Carro;
import com.example.estacionamento.entidades.Vaga;
import com.example.estacionamento.repositories.CarroRepository;
import com.example.estacionamento.repositories.VagaRepository;
import jakarta.transaction.Transactional;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class VagaService {

    private VagaRepository vagaRepository;
    private CarroRepository carroRepository;

    public VagaService(VagaRepository vagaRepository, CarroRepository carroRepository) {
        this.vagaRepository = vagaRepository;
        this.carroRepository = carroRepository;
    }

    public List<Vaga> encontrarTodasAsVagas(){
        return vagaRepository.findAll();
    }

    @Transactional
    public Vaga novaVaga(VagaRecordDto vagaRecordDto){
        Vaga vaga = new Vaga();
        vaga.setNumeroDaVaga(vagaRecordDto.numeroDaVaga());
        System.out.println(vagaRecordDto.numeroDaVaga());
        vaga.setOcupada(false);

        return vagaRepository.save(vaga);

    }

    @Transactional
    public Vaga mudarVaga(VagaRecordDto vagaRecordDto, UUID id) throws NoSuchElementException {
        Vaga vaga = vagaRepository.findById(id).get();

        if(vagaRecordDto.ocupada()){
            if(vaga.getOcupada()){
                System.out.println("Vaga está ocupada");
                return vaga;
            }else {
                Carro carro = new Carro();
                carro.setNome(vagaRecordDto.nomeDoCarro());
                carro.setPlacaDoCarro(vagaRecordDto.placaDoCarro());
                carro.setVaga(vaga);

                vaga.setCarro(carro);
                vaga.setOcupada(true);
                vaga.setHoraDeEntrada(LocalDateTime.now());

                return vagaRepository.save(vaga);


            }
        } else {
            if(!vaga.getOcupada()){
                return vaga;
            } else {
                Carro carro = carroRepository.findCarroByVagaId(vaga.getId());
                vaga.setCarro(null);
                vaga.setOcupada(false);
                vaga.setHoraDeEntrada(null);
                carroRepository.delete(carro);

                return vagaRepository.save(vaga);
            }
        }
    }

    @Scheduled(fixedRate = 10000)
    public void verificarTempo(){
        List<Vaga> vagasOcupadas = vagaRepository.encontrarVagasOcupadas();

        for(Vaga vaga : vagasOcupadas){
            LocalDateTime horaDeEntrada = vaga.getHoraDeEntrada();
            Duration tempoNaVaga = Duration.between(horaDeEntrada, LocalDateTime.now());

            if(tempoNaVaga.toMinutes() >= 2){
                alerta(vaga);
            }
        }
    }

    private void alerta(Vaga vaga) {
        System.out.println("Alerta: carro na vaga " + vaga.getNumeroDaVaga() + " passou do limite de tempo");
    }

    public Optional<Vaga> getVagaById(UUID id){
        Optional<Vaga> vaga = vagaRepository.findById(id);
        return vaga;
    }
}
