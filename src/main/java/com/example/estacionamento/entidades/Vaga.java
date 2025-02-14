package com.example.estacionamento.entidades;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_vaga")
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private Long numeroDaVaga;

    @Column(columnDefinition = "boolean default false")
    private Boolean ocupada;

    private LocalDateTime horaDeEntrada;

    @OneToOne(mappedBy = "vaga", cascade = CascadeType.ALL)
    private Carro carro;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getNumeroDaVaga() {
        return numeroDaVaga;
    }

    public void setNumeroDaVaga(Long numeroDaVaga) {
        this.numeroDaVaga = numeroDaVaga;
    }

    public Boolean getOcupada() {
        return ocupada;
    }

    public void setOcupada(Boolean ocupada) {
        this.ocupada = ocupada;
    }

    public LocalDateTime getHoraDeEntrada() {
        return horaDeEntrada;
    }

    public void setHoraDeEntrada(LocalDateTime horaDeEntrada) {
        this.horaDeEntrada = horaDeEntrada;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }
}
