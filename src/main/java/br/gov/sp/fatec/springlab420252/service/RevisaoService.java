package br.gov.sp.fatec.springlab420252.service;

import java.time.LocalDateTime;
import java.util.List;

import br.gov.sp.fatec.springlab420252.entity.Revisao;

public interface RevisaoService {

    public Revisao nova(Revisao revisao);

    public List<Revisao> todas();

    public List<Revisao> buscarPorTituloSecaoEDataHora(String tituloSecao, LocalDateTime dataHora);

    public Revisao buscarPorId(Long id);
    
}
