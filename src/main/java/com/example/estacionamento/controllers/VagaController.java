package com.example.estacionamento.controllers;

import com.example.estacionamento.dtos.VagaRecordDto;
import com.example.estacionamento.entidades.Vaga;
import com.example.estacionamento.services.VagaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/estacionamento/vagas")
public class VagaController {
    private VagaService vagaService;

    public VagaController(VagaService vagaService) {
        this.vagaService = vagaService;
    }

    @GetMapping
    public ResponseEntity<List<Vaga>> recuperarVagas(){
        return ResponseEntity.status(HttpStatus.FOUND).body(vagaService.encontrarTodasAsVagas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vaga>> recuperarVaga(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.FOUND).body(vagaService.getVagaById(id));
    }

    @PostMapping
    public ResponseEntity<Vaga> novaVaga(@RequestBody VagaRecordDto vagaRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(vagaService.novaVaga(vagaRecordDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaga> mudarVaga(@PathVariable UUID id, @RequestBody VagaRecordDto vagaRecordDto){
        return ResponseEntity.status(HttpStatus.OK).body(vagaService.mudarVaga(vagaRecordDto, id));
    }
}
