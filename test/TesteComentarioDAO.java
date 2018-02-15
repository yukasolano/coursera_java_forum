import model.Conn;
import model.Comentario;
import model.UsuarioDAO;
import model.Usuario;
import model.ComentarioDAO;
import static org.junit.Assert.*;

import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;


public class TesteComentarioDAO {

    JdbcDatabaseTester jdt;
    ComentarioDAO dao;

    @Before
    public void setUp() throws Exception {
        dao = new ComentarioDAO();
        jdt = new JdbcDatabaseTester(Conn.getDriver(),Conn.getDatabase(), Conn.getUser(), Conn.getPassword());
        jdt.setDataSet(new FlatXmlDataFileLoader().load("/inicio.xml"));
        jdt.onSetup();
    }

    @Test
    public void insereUsuario() throws Exception{
        Usuario u = new UsuarioDAO().recuperar("maria");
        Comentario c = new Comentario("este é um novo comentário", u, 1);
        dao.inserir(c);

        ITable currentTable = jdt.getConnection().createDataSet().getTable("COMENTARIO");
        IDataSet expectedDataset = new FlatXmlDataFileLoader().load("/verificaInsercaoComentario.xml");
        ITable expectedTable = expectedDataset.getTable("COMENTARIO");
        ITable filteredCurrentTable = DefaultColumnFilter.excludedColumnsTable(currentTable, new String[]{"id_comentario"});
        ITable filteredExpectedTable = DefaultColumnFilter.excludedColumnsTable(expectedTable, new String[]{"id_comentario"});
        Assertion.assertEquals(filteredExpectedTable, filteredCurrentTable);
    }

    @Test
    public void listaComentarios() throws Exception{
        List<Comentario> comentarios = dao.lista(1);

        assertEquals(2, comentarios.size());
        assertEquals("maria", comentarios.get(0).getUsuario().getLogin());
        assertEquals("joao", comentarios.get(1).getUsuario().getLogin());
        assertEquals(Integer.valueOf(1), comentarios.get(0).getId_topico());
        assertEquals(Integer.valueOf(1), comentarios.get(1).getId_topico());
    }

}
