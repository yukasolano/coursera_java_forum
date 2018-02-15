package model;


public class Comentario {
    String comentario;
    Usuario usuario;
    Integer id_topico;

    public Comentario(String comentario, Usuario usuario, Integer id_topico) {
        this.comentario = comentario;
        this.usuario = usuario;
        this.id_topico = id_topico;
    }

    public String getComentario() {
        return comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Integer getId_topico() {
        return id_topico;
    }
    
}
