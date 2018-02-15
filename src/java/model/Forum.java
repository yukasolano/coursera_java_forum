package model;

import java.util.List;

public class Forum {
    
    public static void adicionaTopico(String titulo, String conteudo, Usuario usuario){
        Topico topico = new Topico(titulo, conteudo, usuario);
        new TopicoDAO().inserir(topico);
        new UsuarioDAO().adicionarPontos(usuario.getLogin(), 10); 
    }
    
    public static void adicionaComentario(String conteudo, String id_topico, Usuario usuario){
        Comentario comentario = new Comentario(conteudo, usuario, Integer.parseInt(id_topico));
        new ComentarioDAO().inserir(comentario);
        new UsuarioDAO().adicionarPontos(usuario.getLogin(), 3);
    }
    
    public static List<Topico> getTopicos(){
        return new TopicoDAO().lista();
    }
    
    public static Topico getTopico(String id_topico){ 
        return new TopicoDAO().recuperaTopico(Integer.parseInt(id_topico));
    }
}
