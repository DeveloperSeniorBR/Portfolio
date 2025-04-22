package com.portfolio.controller;

import com.portfolio.dto.ProjetoDTO;
import com.portfolio.model.Projeto;
import com.portfolio.model.Pessoa;
import com.portfolio.service.ProjetoService;
import com.portfolio.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoRestController {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<Projeto>> listarProjetos() {
        List<Projeto> projetos = projetoService.listarTodos();
        return ResponseEntity.ok(projetos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProjeto(@PathVariable Long id) {
        return projetoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criarProjeto(@RequestBody ProjetoDTO projetoDTO) {
        try {
            Projeto projeto = new Projeto();
            projeto.setNome(projetoDTO.getNome());
            projeto.setDataInicio(projetoDTO.getDataInicio());
            projeto.setDataPrevisaoFim(projetoDTO.getDataPrevisaoFim());
            projeto.setDataFim(projetoDTO.getDataFim());
            projeto.setDescricao(projetoDTO.getDescricao());
            projeto.setStatus(Projeto.Status.valueOf(projetoDTO.getStatus()));
            projeto.setOrcamento(projetoDTO.getOrcamento());
            projeto.setRisco(Projeto.Risco.valueOf(projetoDTO.getRisco()));

            Pessoa gerente = pessoaService.buscarPorId(projetoDTO.getGerenteId())
                    .orElseThrow(() -> new RuntimeException("Gerente não encontrado"));
            
            if (!gerente.isGerente()) {
                return ResponseEntity.badRequest().body("Apenas pessoas com atribuição de gerente podem ser responsáveis por projetos");
            }

            projeto.setGerente(gerente);
            Projeto projetoSalvo = projetoService.salvar(projeto);
            return ResponseEntity.ok(projetoSalvo);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProjeto(@PathVariable Long id, @RequestBody ProjetoDTO projetoDTO) {
        try {
            Projeto projeto = projetoService.buscarPorId(id)
                    .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

            projeto.setNome(projetoDTO.getNome());
            projeto.setDataInicio(projetoDTO.getDataInicio());
            projeto.setDataPrevisaoFim(projetoDTO.getDataPrevisaoFim());
            projeto.setDataFim(projetoDTO.getDataFim());
            projeto.setDescricao(projetoDTO.getDescricao());
            projeto.setStatus(Projeto.Status.valueOf(projetoDTO.getStatus()));
            projeto.setOrcamento(projetoDTO.getOrcamento());
            projeto.setRisco(Projeto.Risco.valueOf(projetoDTO.getRisco()));

            Pessoa gerente = pessoaService.buscarPorId(projetoDTO.getGerenteId())
                    .orElseThrow(() -> new RuntimeException("Gerente não encontrado"));
            
            if (!gerente.isGerente()) {
                return ResponseEntity.badRequest().body("Apenas pessoas com atribuição de gerente podem ser responsáveis por projetos");
            }

            projeto.setGerente(gerente);
            Projeto projetoAtualizado = projetoService.salvar(projeto);
            return ResponseEntity.ok(projetoAtualizado);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirProjeto(@PathVariable Long id) {
        try {
            projetoService.excluir(id);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
} 