package dao;

import domain.Cliente;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class daoServiceImplClientTest {

    private daoService daoService;

    @Test
    public void cadastrarClienteTeste() throws SQLException {
        daoService = new daoServiceImplClient();
        Cliente cliente = new Cliente(10L,"10","Riverson");
        Integer countCad = daoService.cadastrar(cliente);
        assertEquals(1, (int) countCad);

        Cliente clienteBD = daoService.buscar("10");
        assertNotNull(clienteBD);
        assertEquals(cliente.getCodigo(),clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        Integer countDel = daoService.excluir(clienteBD);
        assertEquals(1, (int) countDel);
    }

    @Test
    public void buscaTodosOsClientesTeste() throws SQLException {
        daoService = new daoServiceImplClient();
        Cliente clienteRiverson = new Cliente(null,"10","Riverson");
        Cliente clienteRita = new Cliente(null,"11","Rita");
        Integer countCad = daoService.cadastrar(clienteRiverson);
        Integer cadastrar = daoService.cadastrar(clienteRita);
        assertEquals(1, (int) countCad);
        assertEquals(1, (int) cadastrar);

        List<Cliente> list = daoService.buscaTodos();
        assertNotNull(list);
        assertEquals(2, list.size());

        int countDel = 0;
        for (Cliente cli : list){
            daoService.excluir(cli);
            countDel++;
        }
        assertEquals(list.size(), countDel);
        list = daoService.buscaTodos();
        assertEquals(0,list.size());
    }

    @Test
    public void atualizarTest() throws SQLException {
        daoService = new daoServiceImplClient();
        Cliente cliente = new Cliente(10L,"10","Riverson");
        Integer countCad = daoService.cadastrar(cliente);
        assertEquals(1, (int) countCad);

        Cliente clienteBD = daoService.buscar("10");
        assertNotNull(clienteBD);
        assertEquals(cliente.getCodigo(),clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        clienteBD.setCodigo("20");
        clienteBD.setNome("outro nome");
        Integer countUpdate = daoService.atualizar(clienteBD);
        assertEquals(1, (int) countUpdate);

        Cliente clienteBD1 = daoService.buscar("10");
        assertNull(clienteBD1);

        Cliente clienteBD2 = daoService.buscar("20");
        assertEquals(clienteBD.getId(), clienteBD2.getId());
        assertEquals(clienteBD.getNome(), clienteBD2.getNome());
        assertEquals(clienteBD.getCodigo(), clienteBD2.getCodigo());

        Integer countDel = daoService.excluir(clienteBD);
        assertEquals(1, (int) countDel);
    }
}
