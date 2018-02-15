package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Comentario;
import model.ComentarioDAO;
import model.Forum;
import model.Topico;
import model.TopicoDAO;
import model.Usuario;

@WebServlet(name = "ComentarioController", urlPatterns = {"/comentario"})
public class ComentarioController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String conteudo = request.getParameter("comentario");
        String id_topico = request.getParameter("id_topico");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        Forum.adicionaComentario(conteudo, id_topico, usuario);
        
        response.sendRedirect("topico?id_topico=" + id_topico);
    }  

}
