package br.gov.sp.fatec.springlab420252.entity;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springlab420252.controller.View;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pos_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pos_id")
    @JsonView({View.Post.class})
    private Long id;

    @Column(name = "pos_conteudo")
    @JsonView({View.Post.class})
    private String conteudo;

    @Column(name = "pos_data_hora")
    @JsonView({View.Post.class})
    private LocalDateTime dataHora;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pos_usr_id")
    @JsonView({View.Post.class})
    private Usuario usuario;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    @JsonView({View.Post.class})
    private Set<Reacao> reacoes;

    public Post() {
        this.dataHora = LocalDateTime.now();
    }

    public Post(Long id) {
        this();
        this.id = id;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Reacao> getReacoes() {
        return reacoes;
    }

    public void setReacoes(Set<Reacao> reacoes) {
        this.reacoes = reacoes;
    }

}