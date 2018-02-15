package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UsuarioDAO {

    private static final String database = Conn.getDatabase();
    private static final String user = Conn.getUser();
    private static final String password = Conn.getPassword();

    public void inserir(Usuario u) {	        
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "INSERT INTO usuario(login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, u.getLogin());
            stm.setString(2, u.getEmail());
            stm.setString(3, u.getNome());
            stm.setString(4, u.getSenha());
            stm.setInt(5, u.getPontos());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    public Usuario recuperar(String login) {
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "SELECT * FROM usuario WHERE login = ?;";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, login);
            ResultSet rs = stm.executeQuery();	
            if(rs.next()){
                Usuario u = new Usuario(rs.getString("login"),rs.getString("nome"), 
                        rs.getString("email"), rs.getString("senha"),rs.getInt("pontos")) ;
                return u;
            }			
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Erro ao recuperar usuário: " + e.getMessage());
        }
        return null;
    }

    public Usuario autenticar(String login, String senha) {
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "SELECT * FROM usuario WHERE login = ? AND senha = ?;";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, login);
            stm.setString(2, senha);
            ResultSet rs = stm.executeQuery();	
            if(rs.next()){
                Usuario u = new Usuario(rs.getString("login"),rs.getString("nome"), 
                    rs.getString("email"), rs.getString("senha"),rs.getInt("pontos")) ;
                return u;
            }
            else{
                throw new DAOException("Falha na autenticação!");
            }
        } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new DAOException("Erro ao recuperar usuário: " + e.getMessage());
        }
    }

    public void adicionarPontos(String login, int pontos) {
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "UPDATE usuario SET pontos = pontos + ? WHERE login = ?;";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, pontos);
            stm.setString(2, login);
            stm.executeUpdate();			
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Erro ao adicionar pontos ao usuário: " + e.getMessage());
        }
    }

    public List<Usuario> ranking() {	
        List<Usuario> ranking = new ArrayList<>();	
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "SELECT * FROM usuario ORDER BY pontos DESC;";
            PreparedStatement stm = con.prepareStatement(query);
            ResultSet rs = stm.executeQuery();	
            while(rs.next()){
                    Usuario u = new Usuario(rs.getString("login"),rs.getString("nome"), 
                        rs.getString("email"), rs.getString("senha"),rs.getInt("pontos"));
                    ranking.add(u);
            }		
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Erro ao recuperar lista de usuários: " + e.getMessage());
        }
        return ranking;
    }
}
