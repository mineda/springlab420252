package br.gov.sp.fatec.springlab420252.controller;

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

import br.gov.sp.fatec.springlab420252.entity.Checklist;
import br.gov.sp.fatec.springlab420252.service.ChecklistService;

@RestController
@CrossOrigin
@RequestMapping(value = "/checklist")
public class ChecklistController {

    private ChecklistService service;

    public ChecklistController(ChecklistService service) {
        this.service = service;
    }

    @GetMapping
    public List<Checklist> todos() {
        return service.todos();
    }

    @GetMapping("/buscar")
    public List<Checklist> buscarPorTextoAnotacaoEDescricao(@RequestParam("anotacao") String textoAnotacao, @RequestParam("descricao") String descricao) {
        return service.buscarPorTextoAnotacaoEDescricao(textoAnotacao, descricao);
    }

    @GetMapping("/{id}")
    public Checklist buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Checklist> criar(@RequestBody Checklist checklist) {
        Checklist criado = service.novo(checklist);
        return ResponseEntity.created(URI.create("/checklist/" + criado.getId())).body(criado);
    }

}
