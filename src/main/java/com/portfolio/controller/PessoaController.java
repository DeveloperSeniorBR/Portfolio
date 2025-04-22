package com.portfolio.controller;

import com.portfolio.model.Pessoa;
import com.portfolio.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pessoas", pessoaService.listarTodos());
        return "pessoas/list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("pessoa", new Pessoa());
        return "pessoas/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Pessoa pessoa, RedirectAttributes attr) {
        try {
            pessoaService.salvar(pessoa);
            attr.addFlashAttribute("mensagem", "Pessoa salva com sucesso!");
            return "redirect:/pessoas";
        } catch (Exception e) {
            attr.addFlashAttribute("erro", "Erro ao salvar pessoa: " + e.getMessage());
            return "redirect:/pessoas/form";
        }
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes attr) {
        try {
            Pessoa pessoa = pessoaService.buscarPorId(id)
                    .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada"));
            model.addAttribute("pessoa", pessoa);
            return "pessoas/form";
        } catch (Exception e) {
            attr.addFlashAttribute("erro", "Pessoa não encontrada");
            return "redirect:/pessoas";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes attr) {
        try {
            pessoaService.excluir(id);
            attr.addFlashAttribute("mensagem", "Pessoa excluída com sucesso!");
        } catch (Exception e) {
            attr.addFlashAttribute("erro", "Erro ao excluir pessoa: " + e.getMessage());
        }
        return "redirect:/pessoas";
    }
} 