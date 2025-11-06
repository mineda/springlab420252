package br.gov.sp.fatec.springlab420252.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springlab420252.entity.Financeiro;

public interface FinanceiroRepository extends JpaRepository<Financeiro, Long> {

    public List<Financeiro> findByChecklistDescricaoContainingAndValorGreaterThan(String descricaoChecklist, BigDecimal valorMinimo);
    
}
