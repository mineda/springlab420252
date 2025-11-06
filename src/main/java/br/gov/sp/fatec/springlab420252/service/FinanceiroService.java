package br.gov.sp.fatec.springlab420252.service;

import java.math.BigDecimal;
import java.util.List;

import br.gov.sp.fatec.springlab420252.entity.Financeiro;

public interface FinanceiroService {

    public Financeiro novo(Financeiro financeiro);

    public List<Financeiro> todos();

    public Financeiro buscarPorId(Long id);

    public List<Financeiro> buscarPorDescricaoChecklistEValor(String descricaoChecklist, BigDecimal valor);
}
