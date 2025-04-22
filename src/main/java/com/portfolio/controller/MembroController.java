package com.portfolio.controller;

import com.portfolio.model.Membro;
import com.portfolio.service.MembroService;
import com.portfolio.service.PessoaService;
import com.portfolio.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/membros")
public class MembroController {

    @Autowired
    private MembroService membroService;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ProjetoService projetoService;

    @GetMapping
    public String listarMembros(Model model) {
        model.addAttribute("membros", membroService.listarTodos());
        return "membros/lista";
    }

    @GetMapping("/novo")
    public String novoMembro(Model model) {
        model.addAttribute("membro", new Membro());
        model.addAttribute("pessoas", pessoaService.listarTodos());
        model.addAttribute("projetos", projetoService.listarTodos());
        return "membros/form";
    }

    @PostMapping("/salvar")
    public String salvarMembro(@ModelAttribute Membro membro) {
        membroService.salvar(membro);
        return "redirect:/membros";
    }

    @GetMapping("/{id}/editar")
    public String editarMembro(@PathVariable Long id, Model model) {
        membroService.buscarPorId(id).ifPresent(membro -> {
            model.addAttribute("membro", membro);
            model.addAttribute("pessoas", pessoaService.listarTodos());
            model.addAttribute("projetos", projetoService.listarTodos());
        });
        return "membros/form";
    }

    @PostMapping("/{id}/excluir")
    public String excluirMembro(@PathVariable Long id) {
        membroService.excluir(id);
        return "redirect:/membros";
    }
} 