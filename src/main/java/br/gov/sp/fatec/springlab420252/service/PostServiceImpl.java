package br.gov.sp.fatec.springlab420252.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springlab420252.entity.Post;
import br.gov.sp.fatec.springlab420252.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository repo;

    private UsuarioService usuarioService;

    public PostServiceImpl(PostRepository repo, UsuarioService usuarioService) {
        this.repo = repo;
        this.usuarioService = usuarioService;
    }

    @Override
    public Post novo(Post post) {
        if(post == null ||
                post.getConteudo() == null ||
                post.getConteudo().isBlank() ||
                post.getUsuario() == null ||
                post.getUsuario().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post ou usuário inválido!");
        }
        post.setId(null);
        if(post.getDataHora() == null) {
            post.setDataHora(LocalDateTime.now());
        }
        post.setUsuario(usuarioService.buscarPorId(post.getUsuario().getId()));
        return repo.save(post);
    }

    @Override
    public List<Post> todos() {
        return repo.findAll();
    }

    @Override
    public Post buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post não encontrado!");
        });
    }

    @Override
    public List<Post> buscarPorNomeUsuario(String nomeUsuario) {
        return repo.findByUsuarioNome(nomeUsuario);
    }

}
