package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Forum;
import model.Usuario;

@WebServlet(name = "TopicoController", urlPatterns = {"/topico"})
public class TopicoController extends HttpServlet {
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String id_topico = request.getParameter("id_topico");
        
        if(id_topico == null || id_topico.isEmpty()){
            request.setAttribute("topicos", Forum.getTopicos());
            request.getRequestDispatcher("topicos.jsp").forward(request, response); 
        }
        else{      
            request.setAttribute("topico", Forum.getTopico(id_topico));
            request.getRequestDispatcher("topico.jsp").forward(request, response); 
        }
    } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String titulo = request.getParameter("titulo");
        String conteudo = request.getParameter("conteudo");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        Forum.adicionaTopico(titulo, conteudo, usuario);
        
        response.sendRedirect("/Forum/topico");
    }  

}
