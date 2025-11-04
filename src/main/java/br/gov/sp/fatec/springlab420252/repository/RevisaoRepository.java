package br.gov.sp.fatec.springlab420252.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springlab420252.entity.Revisao;

public interface RevisaoRepository extends JpaRepository<Revisao, Long>{

    public List<Revisao> findBySecaoTituloContainsAndDataHoraGreaterThan(String tituloSecao, LocalDateTime dataHora);
    
}
