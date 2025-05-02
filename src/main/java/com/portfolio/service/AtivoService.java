package com.portfolio.service;

import com.portfolio.model.Ativo;
import com.portfolio.repository.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class AtivoService {

    @Autowired
    private AtivoRepository ativoRepository;

    public List<Ativo> listarTodos() {
        return ativoRepository.findAll();
    }

    public Ativo buscarPorId(Long id) {
        return ativoRepository.findById(id).orElse(null);
    }

    public Ativo salvar(Ativo ativo) {
        return ativoRepository.save(ativo);
    }

    public void excluir(Long id) {
        ativoRepository.deleteById(id);
    }

    public BigDecimal calcularValorTotal() {
        return ativoRepository.findAll().stream()
                .map(a -> a.getValorAtual().multiply(BigDecimal.valueOf(a.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularRendimento() {
        return ativoRepository.findAll().stream()
                .map(a -> a.getValorAtual().subtract(a.getPrecoMedio())
                        .multiply(BigDecimal.valueOf(a.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularVariacao() {
        BigDecimal valorTotal = calcularValorTotal();
        if (valorTotal.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return calcularRendimento().divide(valorTotal, 4, BigDecimal.ROUND_HALF_UP)
                .multiply(BigDecimal.valueOf(100));
    }

    public Map<String, BigDecimal> calcularDistribuicao() {
        BigDecimal valorTotal = calcularValorTotal();
        return ativoRepository.findAll().stream()
                .collect(java.util.stream.Collectors.groupingBy(
                        Ativo::getTipo,
                        java.util.stream.Collectors.reducing(
                                BigDecimal.ZERO,
                                a -> a.getValorAtual().multiply(BigDecimal.valueOf(a.getQuantidade())),
                                BigDecimal::add
                        )
                ));
    }

    public Map<String, BigDecimal> calcularPerformance() {
        // Implementar lógica de performance mensal
        return Map.of();
    }
} 