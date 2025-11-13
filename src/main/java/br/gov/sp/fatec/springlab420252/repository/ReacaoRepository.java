package br.gov.sp.fatec.springlab420252.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springlab420252.entity.Reacao;

public interface ReacaoRepository extends JpaRepository<Reacao, Long> {
    
    @Modifying
    @Query("update Reacao r set r.contador = r.contador + 1 where r.post.id = :postId and r.tipo = :tipo")
    public void incrementarReacao(Long postId, String tipo);

    public Optional<Reacao> findByPostIdAndTipo(Long postId, String tipo);
}
