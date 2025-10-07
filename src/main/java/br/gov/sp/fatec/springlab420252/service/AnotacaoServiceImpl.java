package br.gov.sp.fatec.springlab420252.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springlab420252.entity.Anotacao;
import br.gov.sp.fatec.springlab420252.repository.AnotacaoRepository;

@Service
public class AnotacaoServiceImpl implements AnotacaoService {

    private AnotacaoRepository repo;

    private UsuarioService service;

    public AnotacaoServiceImpl(AnotacaoRepository repo, UsuarioService service) {
        this.repo = repo;
        this.service = service;
    }

    @Override
    public Anotacao nova(Anotacao anotacao) {
        if(anotacao == null ||
                anotacao.getTexto() == null ||
                anotacao.getTexto().isBlank() ||
                anotacao.getUsuario() == null ||
                anotacao.getUsuario().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Texto e usuário são obrigatórios!");
        }
        if(anotacao.getDataHora() == null) {
            anotacao.setDataHora(LocalDateTime.now());
        }
        anotacao.setUsuario(service.buscarPorId(anotacao.getUsuario().getId()));
        return repo.save(anotacao);
    }

    @Override
    public List<Anotacao> todas() {
        return repo.findAll();
    }

    @Override
    public List<Anotacao> buscarPorNomeUsuarioETexto(String nomeUsuario, String texto) {
        return repo.findByUsuarioNomeAndTextoContains(nomeUsuario, texto);
    }
    
}
