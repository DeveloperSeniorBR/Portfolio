package com.portfolio.service;

import com.portfolio.model.Membro;
import com.portfolio.repository.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MembroService {

    @Autowired
    private MembroRepository membroRepository;

    @Transactional
    public Membro salvar(Membro membro) {
        return membroRepository.save(membro);
    }

    @Transactional(readOnly = true)
    public List<Membro> listarTodos() {
        return membroRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Membro> buscarPorId(Long id) {
        return membroRepository.findById(id);
    }

    @Transactional
    public void excluir(Long id) {
        membroRepository.deleteById(id);
    }
} 