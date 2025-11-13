package br.gov.sp.fatec.springlab420252.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springlab420252.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    public List<Post> findByUsuarioNome(String nomeUsuario);
    
}
