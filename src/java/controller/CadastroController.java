package controller;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import model.UsuarioDAO;

@WebServlet(urlPatterns = {"/cadastro"})
public class CadastroController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        request.getRequestDispatcher("cadastro.jsp").forward(request, response);  
    } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Usuario usuario = new Usuario(login, nome, email, senha);
        new UsuarioDAO().inserir(usuario);

        request.getRequestDispatcher("login.jsp").forward(request, response);  
    }  
}
