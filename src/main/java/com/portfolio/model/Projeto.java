package com.portfolio.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "projeto")
public class Projeto {
    
    public enum Status {
        EM_ANALISE("Em Análise"),
        ANALISE_REALIZADA("Análise Realizada"),
        ANALISE_APROVADA("Análise Aprovada"),
        INICIADO("Iniciado"),
        PLANEJADO("Planejado"),
        EM_ANDAMENTO("Em Andamento"),
        ENCERRADO("Encerrado"),
        CANCELADO("Cancelado");

        private final String descricao;

        Status(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    public enum Risco {
        BAIXO("Baixo Risco"),
        MEDIO("Médio Risco"),
        ALTO("Alto Risco");

        private final String descricao;

        Risco(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 200)
    private String nome;
    
    @Column(name = "data_inicio")
    private LocalDate dataInicio;
    
    @Column(name = "data_previsao_fim")
    private LocalDate dataPrevisaoFim;
    
    @Column(name = "data_fim")
    private LocalDate dataFim;
    
    @Column(length = 5000)
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 45)
    private Status status;
    
    private Float orcamento;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 45)
    private Risco risco;
    
    @ManyToOne
    @JoinColumn(name = "idgerente", nullable = false)
    private Pessoa gerente;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataPrevisaoFim() {
        return dataPrevisaoFim;
    }

    public void setDataPrevisaoFim(LocalDate dataPrevisaoFim) {
        this.dataPrevisaoFim = dataPrevisaoFim;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Float getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Float orcamento) {
        this.orcamento = orcamento;
    }

    public Risco getRisco() {
        return risco;
    }

    public void setRisco(Risco risco) {
        this.risco = risco;
    }

    public Pessoa getGerente() {
        return gerente;
    }

    public void setGerente(Pessoa gerente) {
        this.gerente = gerente;
    }

    public boolean podeSerExcluido() {
        return status != Status.INICIADO && 
               status != Status.EM_ANDAMENTO && 
               status != Status.ENCERRADO;
    }
} 