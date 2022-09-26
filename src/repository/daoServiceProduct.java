package repository;

import domain.Cliente;
import domain.Produto;

import java.sql.SQLException;
import java.util.List;

public interface daoServiceProduct {
    Integer cadastrar(Produto produto) throws SQLException;
    Integer atualizar(Produto produto) throws SQLException;
    Produto buscar(String codigo) throws SQLException;
    List<Produto> buscaTodos() throws SQLException;
    Integer excluir(Produto produto) throws SQLException;
}
