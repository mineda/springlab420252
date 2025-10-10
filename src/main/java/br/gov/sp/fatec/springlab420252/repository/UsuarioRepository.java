package br.gov.sp.fatec.springlab420252.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springlab420252.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    public Optional<Usuario> findByNome(String nomeUsuario);
    
}
