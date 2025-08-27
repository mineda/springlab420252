package br.gov.sp.fatec.springlab420252.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springlab420252.entity.Autorizacao;

public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long>{

    public Optional<Autorizacao> findByNome(String nomeAutorizacao);

    @Query("select a from Autorizacao a where a.nome = ?1")
    public Optional<Autorizacao> buscarPorNome(String nomeAutorizacao);

    public List<Autorizacao> findByUsuariosNomeContainsIgnoreCase(String nomeUsuario);

    @Query("select a from Autorizacao a join a.usuarios u where upper(u.nome) like upper(%?1%)")
    public List<Autorizacao> buscarPeloNomeDoUsuario(String nomeUsuario);

    public List<Autorizacao> findByUsuariosNomeAndUsuariosSenha(String nomeUsuario, String senhaUsuario);

    @Query("select a from Autorizacao a join a.usuarios u where u.nome = ?1 and u.senha = ?2")
    public List<Autorizacao> buscarPeloNomeESenhaDoUsuario(String nomeUsuario, String senhaUsuario);
    
}
