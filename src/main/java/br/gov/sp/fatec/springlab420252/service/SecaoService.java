package br.gov.sp.fatec.springlab420252.service;

import java.util.List;

import br.gov.sp.fatec.springlab420252.entity.Secao;

public interface SecaoService {

    public Secao nova(Secao secao);

    public List<Secao> todas();

    public List<Secao> buscarPorTituloSecaoETituloTrabalho(String tituloSecao, String tituloTrabalho);
    
}
