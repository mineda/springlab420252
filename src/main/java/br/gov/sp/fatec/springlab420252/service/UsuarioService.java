package br.gov.sp.fatec.springlab420252.service;

import java.util.List;

import br.gov.sp.fatec.springlab420252.entity.Usuario;

public interface UsuarioService {

    public Usuario novo(Usuario usuario);

    public List<Usuario> buscarTodos();

    public Usuario buscarPorId(Long id);
    
}
