package br.gov.sp.fatec.springlab420252.service;

import java.util.List;

import br.gov.sp.fatec.springlab420252.entity.Comentario;

public interface ComentarioService {

    public Comentario novo(Comentario comentario);

    public List<Comentario> todos();

    public List<Comentario> buscarPorTituloTrabalhoESeveridade(String tituloTrabalho, Integer severidade);

    public Comentario buscarPorId(Long id);
    
}
