package br.gov.sp.fatec.springlab420252.service;

import java.util.List;

import br.gov.sp.fatec.springlab420252.entity.Anotacao;

public interface AnotacaoService {

    public Anotacao nova(Anotacao anotacao);

    public List<Anotacao> todas();
    
    public List<Anotacao> buscarPorNomeUsuarioETexto(String nomeUsuario, String texto);
}
