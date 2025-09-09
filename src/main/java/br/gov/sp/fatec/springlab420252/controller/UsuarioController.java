package br.gov.sp.fatec.springlab420252.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springlab420252.entity.Usuario;
import br.gov.sp.fatec.springlab420252.service.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping(value = "/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        return new ResponseEntity<List<Usuario>>(service.buscarTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> novoUsuario(@RequestBody Usuario usuario) {
        Usuario novo = service.novo(usuario);
        return ResponseEntity.created(URI.create("/usuario/" + novo.getId())).body(novo);
    }

    @GetMapping(value = "/{idUsuario}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable("idUsuario") Long id) {
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }

}