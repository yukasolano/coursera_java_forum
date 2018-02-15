import model.Conn;
import model.UsuarioDAO;
import model.Usuario;
import model.Topico;
import model.TopicoDAO;
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


public class TesteTopicoDAO {

    JdbcDatabaseTester jdt;
    TopicoDAO dao;

    @Before
    public void setUp() throws Exception {
        dao = new TopicoDAO();
        jdt = new JdbcDatabaseTester(Conn.getDriver(),Conn.getDatabase(), Conn.getUser(), Conn.getPassword());
        jdt.setDataSet(new FlatXmlDataFileLoader().load("/inicio.xml"));
        jdt.onSetup();
    }

    @Test
    public void insereTopico() throws Exception{
        Usuario u = new UsuarioDAO().recuperar("maria");
        Topico t = new Topico("novo tópico", "novo conteúdo do tópico", u);
        dao.inserir(t);

        ITable currentTable = jdt.getConnection().createDataSet().getTable("TOPICO");
        IDataSet expectedDataset = new FlatXmlDataFileLoader().load("/verificaInsercaoTopico.xml");
        ITable expectedTable = expectedDataset.getTable("TOPICO");
        ITable filteredCurrentTable = DefaultColumnFilter.excludedColumnsTable(currentTable, new String[]{"id_comentario", "id_topico"});
        ITable filteredExpectedTable = DefaultColumnFilter.excludedColumnsTable(expectedTable, new String[]{"id_comentario", "id_topico"});
        Assertion.assertEquals(filteredExpectedTable, filteredCurrentTable);
    }

    
    @Test
    public void listaTopicos() throws Exception{
        List<Topico> topicos = dao.lista();

        assertEquals(1, topicos.size());
        assertEquals("título do tópico", topicos.get(0).getTitulo()); 
        assertEquals("conteúdo do tópico", topicos.get(0).getConteudo());
        assertEquals("joao", topicos.get(0).getUsuario().getLogin());
    }
    
    @Test
    public void recuperaTopico() throws Exception{
        Topico t = dao.recuperaTopico(1);

        assertEquals("título do tópico", t.getTitulo());  
        assertEquals("conteúdo do tópico", t.getConteudo());
        assertEquals("joao", t.getUsuario().getLogin());
        
        assertEquals(2, t.getComentarios().size());
        assertEquals("maria", t.getComentarios().get(0).getUsuario().getLogin());
        assertEquals("joao", t.getComentarios().get(1).getUsuario().getLogin());
        assertEquals(Integer.valueOf(1), t.getComentarios().get(0).getId_topico());
        assertEquals(Integer.valueOf(1), t.getComentarios().get(1).getId_topico());
    }


}
