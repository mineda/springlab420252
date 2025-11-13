package br.gov.sp.fatec.springlab420252.service;

import java.util.List;

import br.gov.sp.fatec.springlab420252.entity.Post;

public interface PostService {

    public Post novo(Post post);

    public List<Post> todos();

    public Post buscarPorId(Long id);

    public List<Post> buscarPorNomeUsuario(String nomeUsuario);
    
}
