package repository;

import dao.daoServiceImplClient;
import domain.Cliente;
import domain.Produto;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class daoServiceProductImplTest {

    daoServiceProduct daoServiceProduct;

    @Test
    public void cadastrarProduto() throws SQLException {
        daoServiceProduct = new daoServiceProductImpl();
        Produto produto = new Produto(10L,"10","celular", 1200.0);
        Integer countCadastrar = daoServiceProduct.cadastrar(produto);
        assertEquals(1, (int) countCadastrar);

        Integer countExcluir = daoServiceProduct.excluir(produto);
        assertEquals(1, (int) countExcluir);
    }

    @Test
    public void buscaProduto() throws SQLException {
        daoServiceProduct = new daoServiceProductImpl();
        Produto produto = new Produto(10L,"10", "televisao", 2700.0);
        Integer countCadastrar = daoServiceProduct.cadastrar(produto);
        assertEquals(1, (int) countCadastrar);

        Produto produtoBD = daoServiceProduct.buscar("10");
        assertNotNull(produtoBD);
        assertEquals(produtoBD.getCodigo(),produtoBD.getCodigo());
        assertEquals(produtoBD.getNome(), produtoBD.getNome());
        assertEquals(produtoBD.getPreco(), produtoBD.getPreco());

        Integer countExcluir = daoServiceProduct.excluir(produto);
        assertEquals(1, (int) countExcluir);
    }

    @Test
    public void buscaTodosOsProdutos() throws SQLException {
        daoServiceProduct = new daoServiceProductImpl();
        Produto celular = new Produto(10L,"10","celular", 1870.0);
        Produto notebook = new Produto(15L,"20","notebook", 3200.0);
        Integer cadastraCelular = daoServiceProduct.cadastrar(celular);
        Integer cadastraNoteBook = daoServiceProduct.cadastrar(notebook);
        assertEquals(1, (int) cadastraCelular);
        assertEquals(1, (int) cadastraNoteBook);

        List<Produto> produtos = daoServiceProduct.buscaTodos();
        assertNotNull(produtos);
        assertEquals(2, produtos.size());

        int countDel = 0;
        for (Produto prodct : produtos){
            daoServiceProduct.excluir(prodct);
            countDel++;
        }
        assertEquals(produtos.size(), countDel);
        produtos = daoServiceProduct.buscaTodos();
        assertEquals(0,produtos.size());
    }
    
    @Test
    public void atualizaProduto() throws SQLException {
        daoServiceProduct = new daoServiceProductImpl();
        Produto produto = new Produto(10L,"10","celular", 1200.0);
        Integer countCad = daoServiceProduct.cadastrar(produto);
        assertEquals(1, (int) countCad);

        Produto produtoBD = daoServiceProduct.buscar("10");
        assertNotNull(produtoBD);
        assertEquals(produto.getCodigo(),produtoBD.getCodigo());
        assertEquals(produto.getNome(), produtoBD.getNome());
        assertEquals(produto.getPreco(), produtoBD.getPreco());

        produtoBD.setCodigo("20");
        produtoBD.setNome("outro celular");
        produtoBD.setPreco(1500.0);
        Integer countUpdate = daoServiceProduct.atualizar(produtoBD);
        assertEquals(1, (int) countUpdate);

        Produto produtoBD1 = daoServiceProduct.buscar("10");
        assertNull(produtoBD1);

        Produto produtoBD2 = daoServiceProduct.buscar("20");
        assertEquals(produtoBD.getId(), produtoBD2.getId());
        assertEquals(produtoBD.getNome(), produtoBD2.getNome());
        assertEquals(produtoBD.getCodigo(), produtoBD2.getCodigo());
        assertEquals(produtoBD.getPreco(), produtoBD2.getPreco());

        Integer countDel = daoServiceProduct.excluir(produtoBD);
        assertEquals(1, (int) countDel);
    }
}
