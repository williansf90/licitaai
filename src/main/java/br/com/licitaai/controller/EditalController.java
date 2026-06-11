package br.com.licitaai.controller;

import br.com.licitaai.model.Edital;
import br.com.licitaai.repository.EditalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/editais")
public class EditalController {

    @Autowired
    private EditalRepository repository;

    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "editais",
                repository.findAll());

        return "editais/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {

        model.addAttribute(
                "edital",
                new Edital());

        return "editais/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Edital edital) {

        repository.save(edital);

        return "redirect:/editais";
    }

    @GetMapping("/{id}")
    public String visualizar(
            @PathVariable Long id,
            Model model) {

        Edital edital =
                repository.findById(id)
                        .orElseThrow();

        model.addAttribute(
                "edital",
                edital);

        return "editais/detalhes";
    }

    @PostMapping("/analisar/{id}")
    public String analisar(
        @PathVariable Long id) {

        Edital edital =
            repository.findById(id)
                    .orElseThrow();

        edital.setResumo(
            "Resumo automático gerado pela LicitaAI. " +
            "Este edital refere-se à contratação de serviços públicos."
        );

        edital.setRequisitos(
            "Certidões negativas, documentação fiscal " +
            "e proposta comercial."
        );

        repository.save(edital);

        return "redirect:/editais/" + id;
    }
}