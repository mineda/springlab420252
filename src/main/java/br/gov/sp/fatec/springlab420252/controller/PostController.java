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
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springlab420252.entity.Post;
import br.gov.sp.fatec.springlab420252.service.PostService;

@RestController
@CrossOrigin
@RequestMapping(value = "/post")
public class PostController {

    private PostService service;

    public PostController(PostService service) {
        this.service = service;
    }
    
    @GetMapping
    public List<Post> todos() {
        return service.todos();
    }

    @GetMapping("/{id}")
    public Post buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/usuario/{nome}")
    public List<Post> buscarPorNomeUsuario(@PathVariable("nome") String nomeUsuario) {
        return service.buscarPorNomeUsuario(nomeUsuario);
    }

    @PostMapping
    public ResponseEntity<Post> novo(@RequestBody Post post) {
        Post criado = service.novo(post);
        return ResponseEntity.created(URI.create("/post/" + criado.getId())).body(criado);
    }

}
