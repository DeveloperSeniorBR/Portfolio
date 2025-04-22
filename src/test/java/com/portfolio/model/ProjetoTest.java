package com.portfolio.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProjetoTest {

    @Test
    void testPodeSerExcluido_StatusEmAnalise() {
        Projeto projeto = new Projeto();
        projeto.setStatus(Projeto.Status.EM_ANALISE);
        assertTrue(projeto.podeSerExcluido());
    }

    @Test
    void testPodeSerExcluido_StatusAnaliseRealizada() {
        Projeto projeto = new Projeto();
        projeto.setStatus(Projeto.Status.ANALISE_REALIZADA);
        assertTrue(projeto.podeSerExcluido());
    }

    @Test
    void testPodeSerExcluido_StatusAnaliseAprovada() {
        Projeto projeto = new Projeto();
        projeto.setStatus(Projeto.Status.ANALISE_APROVADA);
        assertTrue(projeto.podeSerExcluido());
    }

    @Test
    void testPodeSerExcluido_StatusPlanejado() {
        Projeto projeto = new Projeto();
        projeto.setStatus(Projeto.Status.PLANEJADO);
        assertTrue(projeto.podeSerExcluido());
    }

    @Test
    void testPodeSerExcluido_StatusCancelado() {
        Projeto projeto = new Projeto();
        projeto.setStatus(Projeto.Status.CANCELADO);
        assertTrue(projeto.podeSerExcluido());
    }

    @Test
    void testPodeSerExcluido_StatusIniciado() {
        Projeto projeto = new Projeto();
        projeto.setStatus(Projeto.Status.INICIADO);
        assertFalse(projeto.podeSerExcluido());
    }

    @Test
    void testPodeSerExcluido_StatusEmAndamento() {
        Projeto projeto = new Projeto();
        projeto.setStatus(Projeto.Status.EM_ANDAMENTO);
        assertFalse(projeto.podeSerExcluido());
    }

    @Test
    void testPodeSerExcluido_StatusEncerrado() {
        Projeto projeto = new Projeto();
        projeto.setStatus(Projeto.Status.ENCERRADO);
        assertFalse(projeto.podeSerExcluido());
    }
} 