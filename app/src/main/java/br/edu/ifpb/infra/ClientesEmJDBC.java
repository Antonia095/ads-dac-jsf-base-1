package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.Clientes;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 25/04/2022, 21:45:44
 */
public class ClientesEmJDBC implements Clientes {

   
    private Connection connection;

    public ClientesEmJDBC() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                "jdbc:postgresql://host-banco:5432/livros",
                "job","123"
            );
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }

    }

    
    
    @Override
    public void nova(Cliente cliente) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO cliente(id,nome, dataDeNascimento, cpf) VALUES ( ?, ?, ?, ? );"
            );
            statement.setInt(1,cliente.getId());
            statement.setString(2, cliente.getNome());
            statement.setDate(3, Date.valueOf(cliente.getDataDeNascimento()));
            statement.setString(4, cliente.getCpf());
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public List<Cliente> todas() {
        try {
            List<Cliente> lista = new ArrayList<>();
            ResultSet result = connection.prepareStatement(
                "SELECT * FROM clientes"
            ).executeQuery();
            while (result.next()) {
                lista.add(
                    criarClientes(result)
                );
            }
            return lista;
        } catch (SQLException ex) {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public List<Cliente> porLocalDeOrigem(String localDeOrigem) {
        try {
            List<Cliente> lista = new ArrayList<>();
            PreparedStatement prepareStatement = connection.prepareStatement(
                    "SELECT * FROM cliente WHERE cpf like ?"
            );
            prepareStatement.setString(1, localDeOrigem);
            ResultSet result = prepareStatement.executeQuery();
            while (result.next()) {
                lista.add(
                        criarClientes(result)
                );
            }
            return lista;
        } catch (SQLException ex) {
            return Collections.EMPTY_LIST;
        }
    }

    private Cliente criarClientes(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String nome = result.getString("nome");
        Date dataDeNascimento = result.getDate("dataDeNascimento");
        String cpf = result.getString("cpf");
        
        return new Cliente(id,nome,dataDeNascimento,cpf);
    }

}
