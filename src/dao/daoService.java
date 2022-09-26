package dao;

import domain.Cliente;
import domain.Produto;

import java.sql.SQLException;
import java.util.List;

public interface daoService {
    public Integer cadastrar(Cliente cliente) throws SQLException;
    public Integer atualizar(Cliente cliente) throws SQLException;
    public Cliente buscar(String codigo) throws SQLException;
    public List<Cliente>buscaTodos() throws SQLException;
    public Integer excluir(Cliente cliente) throws SQLException;
}
