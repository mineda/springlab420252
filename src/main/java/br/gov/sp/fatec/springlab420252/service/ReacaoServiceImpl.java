package br.gov.sp.fatec.springlab420252.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springlab420252.entity.Post;
import br.gov.sp.fatec.springlab420252.entity.Reacao;
import br.gov.sp.fatec.springlab420252.repository.ReacaoRepository;

@Service
public class ReacaoServiceImpl implements ReacaoService {

    private ReacaoRepository repo;

    private PostService postService;

    public ReacaoServiceImpl(ReacaoRepository repo, PostService postService) {
        this.repo = repo;
        this.postService = postService;
    }

    @Override
    public Reacao nova(Reacao reacao) {
        if (reacao == null || 
                reacao.getTipo() == null || 
                reacao.getPost() == null ||
                reacao.getPost().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        reacao.setId(null);
        if(reacao.getDataHora() == null) {
            reacao.setDataHora(LocalDateTime.now());
        }
        if(reacao.getContador() == null) {
            reacao.setContador(1);
        }
        reacao.setPost(postService.buscarPorId(reacao.getPost().getId()));
        return repo.save(reacao);
    }

    @Override
    public List<Reacao> todas() {
        return repo.findAll();
    }

    @Override
    public Reacao buscarPorId(Long id) {
        if(id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reação não encontrada!"));
    }

    @Transactional
    @Override
    public void incrementarReacao(Long postId, String tipo) {
        if(postId == null || tipo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(!repo.findByPostIdAndTipo(postId, tipo).isPresent()) {
            Reacao reacao = new Reacao();
            reacao.setPost(new Post(postId));
            reacao.setTipo(tipo);
            reacao.setContador(1);
            nova(reacao);
        }
        else {
            repo.incrementarReacao(postId, tipo);
        }
    }
}
