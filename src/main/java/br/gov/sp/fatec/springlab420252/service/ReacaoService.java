package br.gov.sp.fatec.springlab420252.service;

import java.util.List;

import br.gov.sp.fatec.springlab420252.entity.Reacao;

public interface ReacaoService {

    public Reacao nova(Reacao reacao);

    public List<Reacao> todas();

    public Reacao buscarPorId(Long id);

    public void incrementarReacao(Long postId, String tipo);
    
}
