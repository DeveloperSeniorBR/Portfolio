package com.portfolio.controller;

import com.portfolio.dto.MembroDTO;
import com.portfolio.model.Membro;
import com.portfolio.model.Pessoa;
import com.portfolio.model.Projeto;
import com.portfolio.service.MembroService;
import com.portfolio.service.PessoaService;
import com.portfolio.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/membros")
public class MembroRestController {

    @Autowired
    private MembroService membroService;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ProjetoService projetoService;

    @PostMapping
    public ResponseEntity<?> cadastrarMembro(@RequestBody MembroDTO membroDTO) {
        try {
            // Buscar pessoa
            Pessoa pessoa = pessoaService.buscarPorId(membroDTO.getPessoaId())
                    .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

            // Verificar se a pessoa é funcionário
            if (!pessoa.isFuncionario()) {
                return ResponseEntity.badRequest().body("Apenas funcionários podem ser associados a projetos");
            }

            // Buscar projeto
            Projeto projeto = projetoService.buscarPorId(membroDTO.getProjetoId())
                    .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

            // Criar e salvar membro
            Membro membro = new Membro();
            membro.setPessoa(pessoa);
            membro.setProjeto(projeto);
            membro.setAtribuicao(membroDTO.getAtribuicao());

            Membro membroSalvo = membroService.salvar(membro);
            return ResponseEntity.ok(membroSalvo);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
} 