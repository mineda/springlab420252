package br.gov.sp.fatec.springlab420252.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "chk_checklist")
public class Checklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chk_id")
    private Long id;

    @Column(name = "chk_descricao")
    private String descricao;

    @Column(name = "chk_data_hora_finalizacao")
    private LocalDateTime dataHoraFinalizacao;

    @Column(name = "chk_comentario_finalizacao")
    private String comentarioFinalizacao;

    @ManyToOne
    @JoinColumn(name = "chk_ant_id")
    private Anotacao anotacao;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHoraFinalizacao() {
        return dataHoraFinalizacao;
    }

    public void setDataHoraFinalizacao(LocalDateTime dataHoraFinalizacao) {
        this.dataHoraFinalizacao = dataHoraFinalizacao;
    }

    public String getComentarioFinalizacao() {
        return comentarioFinalizacao;
    }

    public void setComentarioFinalizacao(String comentarioFinalizacao) {
        this.comentarioFinalizacao = comentarioFinalizacao;
    }

    public Anotacao getAnotacao() {
        return anotacao;
    }
    
    public void setAnotacao(Anotacao anotacao) {
        this.anotacao = anotacao;
    }

}
