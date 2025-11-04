package br.gov.sp.fatec.springlab420252.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springlab420252.entity.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

    public List<Comentario> findByTrabalhoTituloContainsAndSeveridadeGreaterThan(String tituloTrabalho, Integer severidade);
    
}
