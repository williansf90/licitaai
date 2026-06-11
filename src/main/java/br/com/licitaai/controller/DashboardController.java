package br.com.licitaai.controller;

import br.com.licitaai.repository.EditalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private EditalRepository repository;

    @GetMapping("/")
    public String dashboard(Model model) {

        long total = repository.count();

        long analisados = repository.findAll()
            .stream()
            .filter(e -> e.getResumo() != null)
            .count();

        long pendentes = total - analisados;

        model.addAttribute("totalEditais", total);
        model.addAttribute("analisados", analisados);
        model.addAttribute("pendentes", pendentes);

        model.addAttribute(
            "ultimosEditais",
            repository.findAll()
        );

        return "dashboard";
    }
}