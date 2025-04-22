package com.portfolio.controller;

import com.portfolio.dto.PessoaDTO;
import com.portfolio.model.Pessoa;
import com.portfolio.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaRestController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        List<Pessoa> pessoas = pessoaService.listarTodos();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPessoa(@PathVariable Long id) {
        return pessoaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criarPessoa(@RequestBody PessoaDTO pessoaDTO) {
        try {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(pessoaDTO.getNome());
            pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
            pessoa.setCpf(pessoaDTO.getCpf());
            pessoa.setFuncionario(pessoaDTO.isFuncionario());
            pessoa.setGerente(pessoaDTO.isGerente());
            pessoa.setAtribuicao(pessoaDTO.getAtribuicao());

            Pessoa pessoaSalva = pessoaService.salvar(pessoa);
            return ResponseEntity.ok(pessoaSalva);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
        try {
            Pessoa pessoa = pessoaService.buscarPorId(id)
                    .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

            pessoa.setNome(pessoaDTO.getNome());
            pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
            pessoa.setCpf(pessoaDTO.getCpf());
            pessoa.setFuncionario(pessoaDTO.isFuncionario());
            pessoa.setGerente(pessoaDTO.isGerente());
            pessoa.setAtribuicao(pessoaDTO.getAtribuicao());

            Pessoa pessoaAtualizada = pessoaService.salvar(pessoa);
            return ResponseEntity.ok(pessoaAtualizada);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirPessoa(@PathVariable Long id) {
        try {
            pessoaService.excluir(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
} 