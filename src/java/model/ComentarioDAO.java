package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ComentarioDAO  {
    private static final String database = Conn.getDatabase();
    private static final String user = Conn.getUser();
    private static final String password = Conn.getPassword();
    
    public void inserir(Comentario c) {		
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "INSERT INTO comentario(comentario, login, id_topico) VALUES (?, ?, ?);";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, c.getComentario());
            stm.setString(2, c.getUsuario().getLogin());
            stm.setInt(3, c.getId_topico()); 
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Erro ao inserir comentário: " + e.getMessage());
        }
    }
    
        public List<Comentario> lista(Integer id_topico) {	
        List<Comentario> comentarios = new ArrayList<>();	
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "SELECT * FROM comentario WHERE id_topico = ? ORDER BY id_comentario;";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, id_topico); 
            ResultSet rs = stm.executeQuery();	
            while(rs.next()){
                    Usuario u = new UsuarioDAO().recuperar(rs.getString("login"));
                    Comentario c = new Comentario(rs.getString("comentario"), u, rs.getInt("id_topico"));
                    comentarios.add(c);
            }		
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Erro ao recuperar lista de comentários: " + e.getMessage());
        }
        return comentarios;
    }
    
}
