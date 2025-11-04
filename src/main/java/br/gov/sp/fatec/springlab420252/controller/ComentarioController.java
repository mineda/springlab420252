package br.gov.sp.fatec.springlab420252.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springlab420252.entity.Comentario;
import br.gov.sp.fatec.springlab420252.service.ComentarioService;

@RestController
@CrossOrigin
@RequestMapping(value = "/comentario")
public class ComentarioController {

    private ComentarioService service;

    public ComentarioController(ComentarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Comentario>> buscarTodos() {
        return new ResponseEntity<List<Comentario>>(service.todos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Comentario> novo(@RequestBody Comentario comentario) {
        Comentario novo = service.novo(comentario);
        return ResponseEntity.created(URI.create("/comentario/" + novo.getId())).body(novo);
    }

    @GetMapping(value = "/busca")
    public ResponseEntity<List<Comentario>> buscarPorTituloENomeUsuario(@RequestParam("titulo") String titulo, @RequestParam("severidade") Integer severidade) {
        return new ResponseEntity<List<Comentario>>(service.buscarPorTituloTrabalhoESeveridade(titulo, severidade), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Comentario> buscarPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<Comentario>(service.buscarPorId(id), HttpStatus.OK);
    }
    
}
