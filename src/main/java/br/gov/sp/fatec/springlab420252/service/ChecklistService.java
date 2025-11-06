package br.gov.sp.fatec.springlab420252.service;

import java.util.List;

import br.gov.sp.fatec.springlab420252.entity.Checklist;

public interface ChecklistService {

    public Checklist novo(Checklist checklist);

    public List<Checklist> todos();

    public Checklist buscarPorId(Long id);

    public List<Checklist> buscarPorTextoAnotacaoEDescricao(String textoAnotacao, String descricao);
    
}
