package br.gov.sp.fatec.springlab420252.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springlab420252.entity.Secao;

public interface SecaoRepository extends JpaRepository<Secao, Long>{

    public List<Secao> findByTituloContainsAndTrabalhoTituloContains(String tituloSecao, String tituloTrabalho);
    
}
