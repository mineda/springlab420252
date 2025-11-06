package br.gov.sp.fatec.springlab420252.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.springlab420252.entity.Financeiro;
import br.gov.sp.fatec.springlab420252.repository.FinanceiroRepository;

@Service
public class FinanceiroServiceImpl implements FinanceiroService {

    private FinanceiroRepository repo;

    private ChecklistService checklistService;

    public FinanceiroServiceImpl(FinanceiroRepository repo, ChecklistService checklistService) {
        this.repo = repo;
        this.checklistService = checklistService;
    }

    @Override
    public Financeiro novo(Financeiro financeiro) {
        if (financeiro == null ||
                financeiro.getValor() == null ||
                financeiro.getValor().doubleValue() <= 0 ||
                financeiro.getGasto() == null ||
                financeiro.getGasto().isBlank() ||
                financeiro.getChecklist() == null ||
                financeiro.getChecklist().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos!");
        }
        if(financeiro.getDescontoPorcentagem() != null &&
                (financeiro.getDescontoPorcentagem() < 0 || 
                financeiro.getDescontoPorcentagem() > 100)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Desconto inválido!");
        }
        financeiro.setId(null); // garante que é um novo registro
        financeiro.setChecklist(checklistService.buscarPorId(financeiro.getChecklist().getId()));
        return repo.save(financeiro);
    }

    @Override
    public List<Financeiro> todos() {
        return repo.findAll();
    }

    @Override
    public Financeiro buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Financeiro não encontrado!"));
    }

    @Override
    public List<Financeiro> buscarPorDescricaoChecklistEValor(String descricaoChecklist, BigDecimal valor) {
        return repo.findByChecklistDescricaoContainingAndValorGreaterThan(descricaoChecklist, valor);
    }

}
