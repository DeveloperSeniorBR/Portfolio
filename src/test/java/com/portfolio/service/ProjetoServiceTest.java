package com.portfolio.service;

import com.portfolio.model.Projeto;
import com.portfolio.repository.ProjetoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjetoServiceTest {

    @Mock
    private ProjetoRepository projetoRepository;

    @InjectMocks
    private ProjetoService projetoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvarProjeto() {
        Projeto projeto = new Projeto();
        projeto.setNome("Projeto Teste");
        projeto.setDataInicio(LocalDate.now());
        projeto.setStatus(Projeto.Status.EM_ANALISE);
        projeto.setOrcamento(100000.0f);

        when(projetoRepository.save(projeto)).thenReturn(projeto);

        Projeto resultado = projetoService.salvar(projeto);

        assertNotNull(resultado);
        assertEquals("Projeto Teste", resultado.getNome());
        verify(projetoRepository).save(projeto);
    }

    @Test
    void testExcluirProjetoEmAnalise() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setStatus(Projeto.Status.EM_ANALISE);

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));

        projetoService.excluir(1L);

        verify(projetoRepository).delete(projeto);
    }

    @Test
    void testExcluirProjetoIniciado() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setStatus(Projeto.Status.INICIADO);

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));

        assertThrows(IllegalStateException.class, () -> projetoService.excluir(1L));
        verify(projetoRepository, never()).delete(projeto);
    }

    @Test
    void testAtualizarStatus() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setStatus(Projeto.Status.EM_ANALISE);

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));
        when(projetoRepository.save(projeto)).thenReturn(projeto);

        Projeto resultado = projetoService.atualizarStatus(1L, Projeto.Status.INICIADO);

        assertNotNull(resultado);
        assertEquals(Projeto.Status.INICIADO, resultado.getStatus());
        verify(projetoRepository).save(projeto);
    }

    @Test
    void testAtualizarStatusProjetoNaoEncontrado() {
        when(projetoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, 
            () -> projetoService.atualizarStatus(1L, Projeto.Status.INICIADO));
    }
} 