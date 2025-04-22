package com.portfolio.controller;

import com.portfolio.model.Projeto;
import com.portfolio.service.ProjetoService;
import com.portfolio.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public String listar(Model model) {
        List<Projeto> projetos = projetoService.listarTodos();
        model.addAttribute("projetos", projetos);
        return "project/projectList";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("projeto", new Projeto());
        model.addAttribute("pessoas", pessoaService.listarTodos());
        return "project/projectCreate";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Projeto projeto) {
        projetoService.salvar(projeto);
        return "redirect:/projetos";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Projeto projeto = projetoService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
        model.addAttribute("projeto", projeto);
        model.addAttribute("pessoas", pessoaService.listarTodos());
        return "project/projectCreate";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        projetoService.excluir(id);
        return "redirect:/projetos";
    }

    @PostMapping("/{id}/status")
    public String atualizarStatus(@PathVariable Long id, @RequestParam("status") Projeto.Status novoStatus) {
        projetoService.atualizarStatus(id, novoStatus);
        return "redirect:/projetos";
    }
} 