package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TopicoDAO  {
    private static final String database = Conn.getDatabase();
    private static final String user = Conn.getUser();
    private static final String password = Conn.getPassword();
    
    public void inserir(Topico t) {		
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "INSERT INTO topico(titulo, conteudo, login) VALUES (?, ?, ?);";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, t.getTitulo());
            stm.setString(2, t.getConteudo());
            stm.setString(3, t.getUsuario().getLogin()); 
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Erro ao inserir tópico: " + e.getMessage());
        }
    }
    
        public List<Topico> lista() {	
        List<Topico> topicos = new ArrayList<>();	
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "SELECT * FROM topico ORDER BY id_topico;";
            PreparedStatement stm = con.prepareStatement(query);
            ResultSet rs = stm.executeQuery();	
            while(rs.next()){
                Usuario u = new UsuarioDAO().recuperar(rs.getString("login"));
                Topico t = new Topico(rs.getInt("id_topico"),rs.getString("titulo"), rs.getString("conteudo"), u);
                topicos.add(t);
            }		
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Erro ao recuperar lista de tópicos: " + e.getMessage());
        }
        return topicos;
    }

    public Topico recuperaTopico(Integer id_topico) {	
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "SELECT * FROM topico WHERE id_topico = ?;";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, id_topico);
            ResultSet rs = stm.executeQuery();	
            if(rs.next()){
                Usuario u = new UsuarioDAO().recuperar(rs.getString("login"));
                Topico t = new Topico(rs.getInt("id_topico"), rs.getString("titulo"), rs.getString("conteudo"), u);
                t.setComentarios(new ComentarioDAO().lista(id_topico));
                return t;
            }		
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Erro ao recuperar tópico: " + e.getMessage());
        }
        return null;
    }
    
}
