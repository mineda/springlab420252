package br.gov.sp.fatec.springlab420252.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springlab420252.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
