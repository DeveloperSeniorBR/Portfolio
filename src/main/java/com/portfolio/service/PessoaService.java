package com.portfolio.service;

import com.portfolio.model.Pessoa;
import com.portfolio.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void excluir(Long id) {
        pessoaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Pessoa> buscarPorId(Long id) {
        return pessoaRepository.findById(id);
    }
} 