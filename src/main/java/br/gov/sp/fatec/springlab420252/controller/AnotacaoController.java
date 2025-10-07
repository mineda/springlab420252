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

import br.gov.sp.fatec.springlab420252.entity.Anotacao;
import br.gov.sp.fatec.springlab420252.service.AnotacaoService;

@RestController
@CrossOrigin
@RequestMapping(value = "/anotacao")
public class AnotacaoController {

    private AnotacaoService service;

    public AnotacaoController(AnotacaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Anotacao>> todas() {
        return new ResponseEntity<List<Anotacao>>(service.todas(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Anotacao> nova(@RequestBody Anotacao anotacao) {
        Anotacao nova = service.nova(anotacao);
        return ResponseEntity.created(URI.create("/anotacao")).body(nova);
    }

    @GetMapping(value = "/textoEUsuario")
    public ResponseEntity<List<Anotacao>> buscarPorTextoENomeUsuario(@RequestParam("texto") String texto, @RequestParam("usuario") String nomeUsuario) {
        return new ResponseEntity<List<Anotacao>>(service.buscarPorNomeUsuarioETexto(nomeUsuario, texto), HttpStatus.OK);
    }    
    
}
