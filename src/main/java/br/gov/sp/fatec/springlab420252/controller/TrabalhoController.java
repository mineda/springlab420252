package br.gov.sp.fatec.springlab420252.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springlab420252.entity.Trabalho;
import br.gov.sp.fatec.springlab420252.service.TrabalhoService;

@RestController
@CrossOrigin
@RequestMapping(value = "/trabalho")
public class TrabalhoController {

    private TrabalhoService service;

    public TrabalhoController(TrabalhoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Trabalho>> buscarTodos() {
        return new ResponseEntity<List<Trabalho>>(service.buscarTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Trabalho> novoTrabalho(@RequestBody Trabalho trabalho) {
        Trabalho novo = service.novoTrabalho(trabalho);
        return ResponseEntity.created(URI.create("/trabalho")).body(novo);
    }

    @GetMapping(value = "/tituloEUsuario")
    public ResponseEntity<List<Trabalho>> buscarPorTituloENomeUsuario(@RequestParam("titulo") String titulo, @RequestParam("usuario") String nomeUsuario) {
        return new ResponseEntity<List<Trabalho>>(service.buscarPorTituloENomeUsuario(titulo, nomeUsuario), HttpStatus.OK);
    }    
    
}
