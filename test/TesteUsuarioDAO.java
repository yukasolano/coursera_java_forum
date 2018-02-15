import model.DAOException;
import model.Conn;
import model.UsuarioDAO;
import model.Usuario;
import static org.junit.Assert.*;

import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;


public class TesteUsuarioDAO {

    JdbcDatabaseTester jdt;
    UsuarioDAO dao;

    @Before
    public void setUp() throws Exception {
        dao = new UsuarioDAO();
        jdt = new JdbcDatabaseTester(Conn.getDriver(),Conn.getDatabase(), Conn.getUser(), Conn.getPassword());
        jdt.setDataSet(new FlatXmlDataFileLoader().load("/inicio.xml"));
        jdt.onSetup();
    }

    @Test
    public void insereUsuario() throws Exception{	
        Usuario u = new Usuario("carlos","Carlos Eduardo", "carlos.eduardo@gmail.com", "minhasenha", 10);
        dao.inserir(u);

        ITable currentTable = jdt.getConnection().createDataSet().getTable("USUARIO");
        IDataSet expectedDataset = new FlatXmlDataFileLoader().load("/verificaInsercao.xml");
        ITable expectedTable = expectedDataset.getTable("USUARIO");
        Assertion.assertEquals(expectedTable, currentTable);
    }

    @Test(expected=DAOException.class)
    public void insereUsuarioExistente() throws Exception{	
        Usuario u = new Usuario("joao","Joao","joao@gmail.com", "minhasenha", 10);
        dao.inserir(u);
    }

    @Test
    public void recuperaUsuario() {
        Usuario u = dao.recuperar("joao");
        assertEquals("joao", u.getLogin());
        assertEquals("Joao Pedro", u.getNome());
        assertEquals("joao@mail.com", u.getEmail());
        assertEquals("senhajoao", u.getSenha());
        assertEquals(Integer.valueOf(5), u.getPontos());
    }

    @Test
    public void recuperaUsuarioInexistente() {
        Usuario u = dao.recuperar("joaogaspar");
        assertEquals(null, u);
    }

    @Test
    public void autenticaUsuario() {
        Usuario u = dao.autenticar("joao", "senhajoao");
        assertEquals("joao", u.getLogin());
        assertEquals("Joao Pedro", u.getNome());
        assertEquals("joao@mail.com", u.getEmail());
        assertEquals("senhajoao", u.getSenha());
        assertEquals(Integer.valueOf(5), u.getPontos());
    }

    @Test(expected=DAOException.class)
    public void autenticaUsuarioInvalido() {
        Usuario u = dao.autenticar("joao", "senhaerrada");
    }

    @Test
    public void adicionaPontos() throws Exception{
        dao.adicionarPontos("joao", 2);	
        ITable currentTable = jdt.getConnection().createDataSet().getTable("USUARIO");
        IDataSet expectedDataset = new FlatXmlDataFileLoader().load("/verificaPontuacao.xml");
        ITable expectedTable = expectedDataset.getTable("USUARIO");
        Assertion.assertEquals(expectedTable, currentTable);
    }

    @Test
    public void listaRanking() throws Exception{
        List<Usuario> ranking = dao.ranking();

        assertEquals(2, ranking.size());
        assertEquals("maria", ranking.get(0).getLogin());
        assertEquals("joao", ranking.get(1).getLogin());
        assertEquals(Integer.valueOf(20), ranking.get(0).getPontos());
        assertEquals(Integer.valueOf(5), ranking.get(1).getPontos());

        dao.adicionarPontos("joao", 22);	
        List<Usuario> ranking2 = dao.ranking();
        assertEquals("joao", ranking2.get(0).getLogin());
    }
}
