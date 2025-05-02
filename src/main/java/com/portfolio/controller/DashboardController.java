package com.portfolio.controller;

import com.portfolio.model.Ativo;
import com.portfolio.service.AtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private AtivoService ativoService;

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("ativos", ativoService.listarTodos());
        model.addAttribute("valorTotal", ativoService.calcularValorTotal());
        model.addAttribute("rendimento", ativoService.calcularRendimento());
        model.addAttribute("variacao", ativoService.calcularVariacao());
        model.addAttribute("distribuicao", ativoService.calcularDistribuicao());
        model.addAttribute("performance", ativoService.calcularPerformance());
        return "index";
    }

    @GetMapping("/novo-ativo")
    public String novoAtivo(Model model) {
        model.addAttribute("ativo", new Ativo());
        return "ativo/form";
    }

    @PostMapping("/salvar-ativo")
    public String salvarAtivo(@ModelAttribute Ativo ativo) {
        ativoService.salvar(ativo);
        return "redirect:/dashboard";
    }

    @GetMapping("/editar-ativo/{id}")
    public String editarAtivo(@PathVariable Long id, Model model) {
        model.addAttribute("ativo", ativoService.buscarPorId(id));
        return "ativo/form";
    }

    @PostMapping("/excluir-ativo/{id}")
    public String excluirAtivo(@PathVariable Long id) {
        ativoService.excluir(id);
        return "redirect:/dashboard";
    }
} 