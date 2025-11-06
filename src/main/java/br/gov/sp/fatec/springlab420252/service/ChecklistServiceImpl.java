package br.gov.sp.fatec.springlab420252.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springlab420252.entity.Checklist;
import br.gov.sp.fatec.springlab420252.repository.ChecklistRepository;

@Service
public class ChecklistServiceImpl implements ChecklistService {

    private ChecklistRepository repo;

    private AnotacaoService anotacaoService;

    public ChecklistServiceImpl(ChecklistRepository repo, AnotacaoService anotacaoService) {
        this.repo = repo;
        this.anotacaoService = anotacaoService;
    }

    @Override
    public Checklist novo(Checklist checklist) {
        if(checklist == null ||
                checklist.getDescricao() == null ||
                checklist.getDescricao().isBlank() ||
                checklist.getAnotacao() == null ||
                checklist.getAnotacao().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos!");
        }
        checklist.setId(null); //garante que é um novo registro
        checklist.setAnotacao(anotacaoService.buscarPorId(checklist.getAnotacao().getId()));
        return repo.save(checklist);
    }

    @Override
    public List<Checklist> todos() {
        return repo.findAll();
    }

    @Override
    public Checklist buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Checklist não encontrado!");
        });
    }

    @Override
    public List<Checklist> buscarPorTextoAnotacaoEDescricao(String textoAnotacao, String descricao) {
        return repo.findByAnotacaoTextoContainingAndDescricaoContaining(textoAnotacao, descricao);
    }

}
