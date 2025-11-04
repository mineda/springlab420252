package br.gov.sp.fatec.springlab420252.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springlab420252.entity.Comentario;
import br.gov.sp.fatec.springlab420252.repository.ComentarioRepository;

@Service
public class ComentarioServiceImpl implements ComentarioService{

    private ComentarioRepository repo;

    private TrabalhoService trabService;

    public ComentarioServiceImpl(ComentarioRepository repo, TrabalhoService trabService) {
        this.repo = repo;
        this.trabService = trabService;
    }

    @Override
    public Comentario novo(Comentario comentario) {
        if(comentario == null ||
                comentario.getConteudo() == null ||
                comentario.getConteudo().isBlank() ||
                comentario.getSeveridade() == null ||
                comentario.getSeveridade() <= 0 ||
                comentario.getSeveridade() > 3 ||
                comentario.getTrabalho() == null ||
                comentario.getTrabalho().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados obrigatórios não informados!");
        }
        if(comentario.getDataHora() == null) {
            comentario.setDataHora(LocalDateTime.now());
        }
        comentario.setTrabalho(trabService.buscarPorId(comentario.getTrabalho().getId()));
        return repo.save(comentario);
    }

    @Override
    public List<Comentario> todos() {
        return repo.findAll();
    }

    @Override
    public List<Comentario> buscarPorTituloTrabalhoESeveridade(String tituloTrabalho, Integer severidade) {
        return repo.findByTrabalhoTituloContainsAndSeveridadeGreaterThan(tituloTrabalho, severidade);
    }

    @Override
    public Comentario buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentário não encontrado!");
        });
    }
    
}
