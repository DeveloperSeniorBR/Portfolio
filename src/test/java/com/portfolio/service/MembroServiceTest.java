package com.portfolio.service;

import com.portfolio.model.Membro;
import com.portfolio.model.Pessoa;
import com.portfolio.model.Projeto;
import com.portfolio.repository.MembroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MembroServiceTest {

    @Mock
    private MembroRepository membroRepository;

    @InjectMocks
    private MembroService membroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvarMembro() {
        Membro membro = new Membro();
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João Silva");
        membro.setPessoa(pessoa);
        
        Projeto projeto = new Projeto();
        projeto.setNome("Projeto Teste");
        membro.setProjeto(projeto);

        when(membroRepository.save(membro)).thenReturn(membro);

        Membro resultado = membroService.salvar(membro);

        assertNotNull(resultado);
        assertEquals("João Silva", resultado.getPessoa().getNome());
        verify(membroRepository).save(membro);
    }

    @Test
    void testListarTodosMembros() {
        Membro membro1 = new Membro();
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("João Silva");
        membro1.setPessoa(pessoa1);

        Membro membro2 = new Membro();
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Maria Santos");
        membro2.setPessoa(pessoa2);

        List<Membro> membros = Arrays.asList(membro1, membro2);
        when(membroRepository.findAll()).thenReturn(membros);

        List<Membro> resultado = membroService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(membroRepository).findAll();
    }

    @Test
    void testBuscarMembroPorId() {
        Membro membro = new Membro();
        membro.setId(1L);
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João Silva");
        membro.setPessoa(pessoa);

        when(membroRepository.findById(1L)).thenReturn(Optional.of(membro));

        Optional<Membro> resultado = membroService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("João Silva", resultado.get().getPessoa().getNome());
        verify(membroRepository).findById(1L);
    }
} 