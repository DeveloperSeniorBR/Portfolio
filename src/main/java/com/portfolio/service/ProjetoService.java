package com.portfolio.service;

import com.portfolio.model.Projeto;
import com.portfolio.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Transactional
    public Projeto salvar(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    @Transactional
    public void excluir(Long id) {
        Optional<Projeto> projetoOpt = projetoRepository.findById(id);
        if (projetoOpt.isPresent()) {
            Projeto projeto = projetoOpt.get();
            Projeto.Status status = projeto.getStatus();
            if (status == null || (status != Projeto.Status.INICIADO && 
                                 status != Projeto.Status.EM_ANDAMENTO && 
                                 status != Projeto.Status.ENCERRADO)) {
                projetoRepository.delete(projeto);
            } else {
                throw new IllegalStateException("Não é possível excluir um projeto com status iniciado, em andamento ou encerrado");
            }
        }
    }

    @Transactional(readOnly = true)
    public List<Projeto> listarTodos() {
        return projetoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Projeto> buscarPorId(Long id) {
        return projetoRepository.findById(id);
    }

    @Transactional
    public Projeto atualizarStatus(Long id, Projeto.Status novoStatus) {
        Optional<Projeto> projetoOpt = projetoRepository.findById(id);
        if (projetoOpt.isPresent()) {
            Projeto projeto = projetoOpt.get();
            projeto.setStatus(novoStatus);
            return projetoRepository.save(projeto);
        }
        throw new IllegalArgumentException("Projeto não encontrado");
    }
} 