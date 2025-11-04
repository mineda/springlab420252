package br.gov.sp.fatec.springlab420252.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rev_revisao")
public class Revisao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rev_id")
    private Long id;

    @Column(name = "rev_feedback")
    private String feedback;

    @Column(name = "rev_data_hora")
    private LocalDateTime dataHora;

    @Column(name = "rev_data_hora_correcao")
    private LocalDateTime dataHoraCorrecao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rev_sec_id")
    private Secao secao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public LocalDateTime getDataHoraCorrecao() {
        return dataHoraCorrecao;
    }

    public void setDataHoraCorrecao(LocalDateTime dataHoraCorrecao) {
        this.dataHoraCorrecao = dataHoraCorrecao;
    }

    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }
    
}
