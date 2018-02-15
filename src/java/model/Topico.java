package model;


import java.util.ArrayList;
import java.util.List;


public class Topico {
    
    private String titulo;
    private String conteudo;
    private Usuario usuario;
    private List<Comentario> comentarios;
    private Integer id_topico;

    public Topico(Integer id_topico, String titulo, String conteudo, Usuario usuario) {
        this.id_topico = id_topico;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.usuario = usuario;
        this.comentarios = new ArrayList<>();
    }

    public Topico(String titulo, String conteudo, Usuario usuario) {
        this.id_topico = -1;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.usuario = usuario;
        this.comentarios = new ArrayList<>();
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    public Integer getId_topico() {
        return id_topico;
    }
    
    
}
