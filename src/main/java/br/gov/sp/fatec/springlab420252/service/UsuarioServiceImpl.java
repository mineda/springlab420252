package br.gov.sp.fatec.springlab420252.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springlab420252.entity.Autorizacao;
import br.gov.sp.fatec.springlab420252.entity.Usuario;
import br.gov.sp.fatec.springlab420252.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springlab420252.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private UsuarioRepository usuarioRepo;

    private AutorizacaoRepository autRepo;

    private PasswordEncoder encoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepo, 
            AutorizacaoRepository autRepo,
            PasswordEncoder encoder) {
        this.usuarioRepo = usuarioRepo;
        this.autRepo = autRepo;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public Usuario novo(Usuario usuario) {
        if(usuario == null ||
                usuario.getNome() == null ||
                usuario.getNome().isBlank() ||
                usuario.getSenha() == null ||
                usuario.getSenha().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados de usuário inválidos!");
        }
        usuario.setId(null);
        Set<Autorizacao> autorizacoes = new HashSet<Autorizacao>();
        for(Autorizacao aut: usuario.getAutorizacoes()) {
            autorizacoes.add(buscarAutorizacaoPorId(aut.getId()));
        }
        usuario.setAutorizacoes(autorizacoes);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepo.save(usuario);
    }

    private Autorizacao buscarAutorizacaoPorId(Long id) {
        if(id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta id da autorização!");
        }
        return autRepo.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Autorização não encontrada!");
        });
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepo.findAll();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Usuario buscarPorId(Long id) {
        Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
        if(usuarioOp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
        }
        return usuarioOp.get();
    }
    
}
