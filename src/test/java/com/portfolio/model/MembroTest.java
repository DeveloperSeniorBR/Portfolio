package com.portfolio.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MembroTest {

    @Test
    void testCriarMembroComAtribuicao() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João Silva");
        pessoa.setFuncionario(true);
        pessoa.setAtribuicao("Desenvolvedor");

        Projeto projeto = new Projeto();
        projeto.setNome("Projeto Teste");

        Membro membro = new Membro();
        membro.setPessoa(pessoa);
        membro.setProjeto(projeto);
        membro.setAtribuicao("Desenvolvedor Backend");

        assertNotNull(membro.getPessoa());
        assertNotNull(membro.getProjeto());
        assertEquals("Desenvolvedor Backend", membro.getAtribuicao());
    }

    @Test
    void testMembroComPessoaNaoFuncionario() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João Silva");
        pessoa.setFuncionario(false);
        pessoa.setAtribuicao("Consultor");

        Projeto projeto = new Projeto();
        projeto.setNome("Projeto Teste");

        Membro membro = new Membro();
        membro.setPessoa(pessoa);
        membro.setProjeto(projeto);
        membro.setAtribuicao("Consultor");

        assertNotNull(membro.getPessoa());
        assertFalse(membro.getPessoa().isFuncionario());
    }
} 