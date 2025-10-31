package br.gov.sp.fatec.springlab420252.service;

import java.util.List;

import br.gov.sp.fatec.springlab420252.entity.Trabalho;

public interface TrabalhoService {

    public List<Trabalho> buscarTodos();

    public Trabalho novoTrabalho(Trabalho trabalho);

    public Trabalho buscarPorId(Long id);

    public List<Trabalho> buscarPorTituloENomeUsuario(String titulo, String nomeUsuario);
    
}
