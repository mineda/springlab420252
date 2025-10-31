package br.gov.sp.fatec.springlab420252.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springlab420252.entity.Trabalho;
import br.gov.sp.fatec.springlab420252.repository.TrabalhoRepository;

@Service
public class TrabalhoServiceImpl implements TrabalhoService{

    private TrabalhoRepository trabalhoRepo;

    private UsuarioService usuarioService;

    public TrabalhoServiceImpl(TrabalhoRepository trabalhoRepo, UsuarioService usuarioService) {
        this.trabalhoRepo = trabalhoRepo;
        this.usuarioService = usuarioService;
    }

    @Override
    public List<Trabalho> buscarTodos() {
        return trabalhoRepo.findAll();
    }

    @Override
    public Trabalho novoTrabalho(Trabalho trabalho) {
        if(trabalho == null ||
                trabalho.getTitulo() == null ||
                trabalho.getTitulo().isBlank() ||
                trabalho.getUsuario() == null ||
                trabalho.getUsuario().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Necessário informar título e id do usuário!");
        }
        if(trabalho.getDataHoraEntrega() == null) {
            trabalho.setDataHoraEntrega(LocalDateTime.now());
        }
        trabalho.setUsuario(usuarioService.buscarPorId(trabalho.getUsuario().getId()));
        return trabalhoRepo.save(trabalho);
    }

    @Override
    public List<Trabalho> buscarPorTituloENomeUsuario(String titulo, String nomeUsuario) {
        return trabalhoRepo.buscarPorTituloENomeUsuario(titulo, nomeUsuario);
    }

    @Override
    public Trabalho buscarPorId(Long id) {
        return trabalhoRepo.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trabalho com esse id não existe!");
        });
    }
    
}
