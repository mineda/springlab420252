package br.gov.sp.fatec.springlab420252.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springlab420252.entity.Checklist;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {

    public List<Checklist> findByAnotacaoTextoContainingAndDescricaoContaining(String textoAnotacao, String descricao);
    
}
