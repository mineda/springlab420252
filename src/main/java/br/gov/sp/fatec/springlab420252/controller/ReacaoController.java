package br.gov.sp.fatec.springlab420252.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springlab420252.entity.Reacao;
import br.gov.sp.fatec.springlab420252.service.ReacaoService;

@RestController
@CrossOrigin
@RequestMapping(value = "/reacao")
public class ReacaoController {

    private ReacaoService service;

    public ReacaoController(ReacaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Reacao> todas() {
        return service.todas();
    }

    @GetMapping("/{id}")
    public Reacao buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }

    @PatchMapping("/incrementar")
    public void incrementarReacao(@RequestParam("postId") Long postId, @RequestParam("tipo") String tipo) {
        service.incrementarReacao(postId, tipo);
    }

    @PostMapping
    public ResponseEntity<Reacao> nova(@RequestBody Reacao reacao) {
        Reacao criada = service.nova(reacao);
        return ResponseEntity.created(URI.create("/reacao/" + criada.getId())).body(criada);
    }

}
