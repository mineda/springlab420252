package br.gov.sp.fatec.springlab420252.entity;

import java.time.LocalDateTime;

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
import jakarta.persistence.Table;

@Entity
@Table(name = "rea_reacao")
public class Reacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rea_id")
    @JsonView({View.Post.class})
    private Long id;
    
    @Column(name = "rea_tipo")
    @JsonView({View.Post.class})
    private String tipo;

    @Column(name = "rea_data_hora")
    @JsonView({View.Post.class})
    private LocalDateTime dataHora;

    @Column(name = "rea_contador")
    @JsonView({View.Post.class})
    private Integer contador;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rea_pos_id")
    private Post post;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getContador() {
        return contador;
    }

    public void setContador(Integer contador) {
        this.contador = contador;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
