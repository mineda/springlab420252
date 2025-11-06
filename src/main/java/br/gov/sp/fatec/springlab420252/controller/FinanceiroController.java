package br.gov.sp.fatec.springlab420252.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springlab420252.entity.Financeiro;
import br.gov.sp.fatec.springlab420252.service.FinanceiroService;

@RestController
@CrossOrigin
@RequestMapping(value = "/financeiro")
public class FinanceiroController {

    private FinanceiroService service;

    public FinanceiroController(FinanceiroService service) {
        this.service = service;
    }

    @GetMapping
    public List<Financeiro> todos() {
        return service.todos();
    }

    @GetMapping("/{id}")
    public Financeiro buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/buscar")
    public List<Financeiro> buscarPorDescricaoChecklistEValor(@RequestParam("checklist") String descricaoChecklist, @RequestParam("valor") BigDecimal valor) {
        return service.buscarPorDescricaoChecklistEValor(descricaoChecklist, valor);
    }

    @PostMapping
    public ResponseEntity<Financeiro> criar(@RequestBody Financeiro financeiro) {
        Financeiro criado = service.novo(financeiro);
        return ResponseEntity.created(URI.create("/financeiro/" + criado.getId())).body(criado);
    }

}
