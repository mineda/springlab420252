package br.gov.sp.fatec.springlab420252.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "fin_financeiro")
public class Financeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fin_id")
    private Long id;

    @Column(name = "fin_gasto")
    private String gasto;

    @Column(name = "fin_valor")
    private BigDecimal valor;

    @Column(name = "fin_desconto_porc")
    private Integer descontoPorcentagem;

    @ManyToOne
    @JoinColumn(name = "fin_chk_id")
    private Checklist checklist;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getGasto() {
        return gasto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getDescontoPorcentagem() {
        return descontoPorcentagem;
    }

    public Checklist getChecklist() {
        return checklist;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGasto(String gasto) {
        this.gasto = gasto;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setDescontoPorcentagem(Integer descontoPorcentagem) {
        this.descontoPorcentagem = descontoPorcentagem;
    }

    public void setChecklist(Checklist checklist) {
        this.checklist = checklist;
    }

}
